package com.exos.services.modelmapper;

import com.exos.services.domain.UserVO;
import com.exos.services.model.User;
import org.springframework.stereotype.Component;

@Component
public class ModelToDomainMapper {

    public UserVO mapModelToDomain(User user){
        return UserVO.builder()
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .qualification(user.getQualification())
                .type(user.getType())
                .build();
    }
}
