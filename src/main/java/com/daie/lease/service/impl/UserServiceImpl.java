package com.daie.lease.service.impl;

import com.daie.lease.model.pojo.User;
import com.daie.lease.repository.UserRepository;
import com.daie.lease.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        Optional<User> userOpt = userRepository.findById(id);
        return userOpt.orElseThrow(() -> new Exception("user not found"));
    }
}
