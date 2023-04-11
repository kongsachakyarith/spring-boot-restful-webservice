package com.java.springboot.service;

import com.java.springboot.dto.UserDto;
import com.java.springboot.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);

   void deleteUser(Long userId);
}
