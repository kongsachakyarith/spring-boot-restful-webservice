package com.java.springboot.service.serviceImpl;

import com.java.springboot.entity.User;
import com.java.springboot.repository.UserRepository;
import com.java.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
