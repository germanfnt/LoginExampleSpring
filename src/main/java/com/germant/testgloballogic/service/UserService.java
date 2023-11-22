package com.germant.testgloballogic.service;

import com.germant.testgloballogic.model.UserEntity;

public interface UserService {
    void save(UserEntity userEntity);

    boolean existByEmail(String email);

    UserEntity findUserByEmail(String email);

    void updateLastLogin(String email);

}
