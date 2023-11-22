package com.germant.testgloballogic.mapper;

import com.germant.testgloballogic.model.UserEntity;
import com.germant.testgloballogic.model.request.PhoneRequest;
import com.germant.testgloballogic.model.response.LoginResponse;
import org.hibernate.MappingException;

public class LoginMapper {
    public static LoginResponse mapper(UserEntity entity) {
        try {
            PhoneRequest phoneRequest = null;
            if (entity.getPhoneEntity() != null){
                phoneRequest = new PhoneRequest();
                phoneRequest.setId(entity.getPhoneEntity().getId());
                phoneRequest.setNumber(entity.getPhoneEntity().getNumber());
                phoneRequest.setCityCode(entity.getPhoneEntity().getCityCode());
                phoneRequest.setCountryCode(entity.getPhoneEntity().getCountryCode());
            }
            return new LoginResponse(entity.getId(), entity.getDateRegister(), entity.getLastAccess(), entity.getEmail(), entity.isActive(), entity.getName(), entity.getEmail(), entity.getPassword(), phoneRequest);
        } catch (Exception e) {
            throw new MappingException("Error to map Login response");
        }

    }
}
