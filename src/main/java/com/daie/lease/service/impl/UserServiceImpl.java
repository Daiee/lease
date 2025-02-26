package com.daie.lease.service.impl;

import com.daie.lease.model.pojo.User;
import com.daie.lease.repository.UserRepository;
import com.daie.lease.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String signup(String username, String password) throws Exception {
        User existUser = userRepository.findByUsername(username);
        assert existUser == null;
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return user.getUsername();
    }

    @Override
    public User login(String username, String password) throws Exception {
        User existUser = userRepository.findByUsername(username);
        assert existUser != null;
        if (existUser.getPassword().equals(password)) {
            return existUser;
        }
        throw new Exception("密码错误");
    }
}
