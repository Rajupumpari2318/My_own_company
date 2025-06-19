package com.exos.services.modelmapper;

import com.exos.services.domain.UserVO;
import com.exos.services.model.User;
import org.springframework.stereotype.Component;

@Component
public class DomainToModelMapper {

    public User mapUserVOToUser(UserVO userVO){
        return User.builder()
                .firstName(userVO.getFirstName())
                .middleName(userVO.getMiddleName())
                .lastName(userVO.getLastName())
                .email(userVO.getEmail())
                .mobile(userVO.getMobile())
                .qualification(userVO.getQualification())
                .type(userVO.getType())
                .build();
    }

    public User mapUserVOToExistingUser(UserVO userVO, User user){
        user.setFirstName(userVO.getFirstName());
        user.setMiddleName(userVO.getMiddleName());
        user.setLastName(userVO.getLastName());
        user.setMobile(userVO.getMobile());
        user.setQualification(userVO.getQualification());
        user.setType(userVO.getType());
        return user;
    }
}
