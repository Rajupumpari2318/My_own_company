package com.exos.services.service;

import com.exos.services.domain.UserVO;

import java.util.Set;

public interface UserService {
    UserVO saveUser(UserVO userVO);
    Set<UserVO> saveUsers(Set<UserVO> userVOList);
    UserVO getUserById(String email);
    UserVO updateUser(UserVO userVO, String email);
}
