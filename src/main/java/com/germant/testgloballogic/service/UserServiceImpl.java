package com.germant.testgloballogic.service;

import com.germant.testgloballogic.model.Role;
import com.germant.testgloballogic.model.UserEntity;
import com.germant.testgloballogic.repository.RoleRepository;
import com.germant.testgloballogic.repository.UserRepository;
import com.germant.testgloballogic.util.EncodePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserEntity userEntity) {
        userEntity.setPassword(EncodePassword.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public void updateLastLogin(String email){
       UserEntity user = this.findUserByEmail(email);
       user.setLastAccess(LocalDateTime.now());
       userRepository.save(user);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).get();
    }
}
