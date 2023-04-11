package com.java.springboot.service.serviceImpl;

import com.java.springboot.dto.UserDto;
import com.java.springboot.entity.User;
import com.java.springboot.exception.EmailAlreadyExistsException;
import com.java.springboot.exception.ResourceNotFoundException;
import com.java.springboot.mapper.AutoUserMapper;
import com.java.springboot.mapper.UserMapper;
import com.java.springboot.repository.UserRepository;
import com.java.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        //Convert UserDto into User JPA Entity
        //User user = UserMapper.mapToUser(userDto);

        //User user = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User saveUser = userRepository.save(user);

        // Convert User JPA entity to UserDto
        //UserDto saveUserDto = UserMapper.mapToUserDto(saveUser);

        //UserDto saveUserDto = modelMapper.map(saveUser, UserDto.class);

        UserDto saveUserDto = AutoUserMapper.MAPPER.mapToUserDto(user);
        return saveUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user","id",userId)
        );


//        return modelMapper.map(user, UserDto.class);

        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        //return users.stream().map(UserMapper::mapToUserDto)
                //.collect(Collectors.toList());

        return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("user", "id", user.getId())
        );
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser =  userRepository.save(existingUser);
        //return UserMapper.mapToUserDto(updatedUser);
        //return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user", "id",userId)
        );
        userRepository.deleteById(userId);
    }
}
