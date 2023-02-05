package com.dark.news.service.impl;

import com.dark.news.database.entity.user.UserEntity;
import com.dark.news.database.repository.UserRepository;
import com.dark.news.exceptions.EntryAlreadyExistException;
import com.dark.news.service.UserService;
import com.dark.news.service.model.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Locale;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public boolean userPasswordIsValid(UserModel userModel) {
        UserEntity user = userRepository.findByUsername(userModel.getUsername());
        return passwordEncoder.matches(userModel.getPassword(), user.getPassword());
    }

    @Override
    public UserModel findUserByUsername(String username) {
        return new UserModel(userRepository.findByUsername(username));
    }

    @Override
    public boolean userExist(String username) {

        return userRepository.findByUsername(username) != null;
    }

    @Override
    public UserModel registerNewUserAccount(UserModel userModel) throws EntryAlreadyExistException {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setUsername(userModel.getUsername().toLowerCase(Locale.ROOT));
        if (userExist(userModel.getUsername())) {
            throw new EntryAlreadyExistException("There is an account with that username: "
                    + userModel.getUsername());
        }
        UserEntity user = new UserEntity(userModel);
        UserEntity registeredUser = userRepository.save(user);
        return new UserModel(registeredUser);
    }

    @Override
    public UserModel updateUserAccount(UserModel userModel) {
        UserEntity user = new UserEntity(userModel);
        userRepository.save(user);
        return new UserModel(user);
    }
}
