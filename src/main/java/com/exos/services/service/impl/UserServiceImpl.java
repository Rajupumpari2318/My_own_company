package com.exos.services.service.impl;

import com.exos.services.exception.RecordNotFoundException;
import com.exos.services.modelmapper.DomainToModelMapper;
import com.exos.services.modelmapper.ModelToDomainMapper;
import com.exos.services.repository.UserRepository;
import com.exos.services.domain.UserVO;
import com.exos.services.model.User;
import com.exos.services.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DomainToModelMapper modelMapper;
    @Autowired
    ModelToDomainMapper domainMapper;

    @Override
    public UserVO saveUser(UserVO userVO) {
        User user = modelMapper.mapUserVOToUser(userVO);
        user = userRepository.save(user);
        return domainMapper.mapModelToDomain(user);
    }

    @Override
    public Set<UserVO> saveUsers(Set<UserVO> userVOList) {
        List<User> users = userVOList.stream().map(modelMapper::mapUserVOToUser).collect(Collectors.toList());
        users = (List<User>) userRepository.saveAll(users);
        userVOList = users.stream().map(domainMapper::mapModelToDomain).collect(Collectors.toSet());
        return userVOList;
    }

    @Override
    public UserVO getUserById(String email) {
        Optional<User> optUser = userRepository.findById(email);
        return optUser.map(user -> domainMapper.mapModelToDomain(user)).orElse(null);
    }

    @Override
    public UserVO updateUser(UserVO userVO, String email) throws RecordNotFoundException {
        Optional<User> optUser = userRepository.findById(email);
        if(optUser.isPresent()){
            User user = modelMapper.mapUserVOToExistingUser(userVO, optUser.get());
            userRepository.save(user);
            return domainMapper.mapModelToDomain(user);
        }else{
            throw new RecordNotFoundException("User Not Found with email "+ email);
        }
    }
}
