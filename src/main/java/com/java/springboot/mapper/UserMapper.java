package com.java.springboot.mapper;

import com.java.springboot.dto.UserDto;
import com.java.springboot.entity.User;

public class UserMapper {

    // Covert User JPA Entity into UserDto
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    // Convert UserDto into User JPA Entity
    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getFirstName(),
                userDto.getEmail()
        );
        return user;
    }
}
