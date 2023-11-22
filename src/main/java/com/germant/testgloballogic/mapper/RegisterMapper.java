package com.germant.testgloballogic.mapper;

import com.germant.testgloballogic.model.UserEntity;
import com.germant.testgloballogic.model.response.RegisterResponse;
import org.springframework.data.mapping.MappingException;

public class RegisterMapper {
    public static RegisterResponse mapper(UserEntity userByEmail) {
        try {
            return new RegisterResponse(userByEmail.getId(), userByEmail.getDateRegister(), userByEmail.getLastAccess(), null, userByEmail.isActive());
        } catch (Exception e) {
            throw new MappingException("Error to map response");
        }
    }
}
