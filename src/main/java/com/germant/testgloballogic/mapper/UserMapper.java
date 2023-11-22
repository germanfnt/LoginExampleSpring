package com.germant.testgloballogic.mapper;

import com.germant.testgloballogic.model.PhoneEntity;
import com.germant.testgloballogic.model.Role;
import com.germant.testgloballogic.model.UserEntity;
import com.germant.testgloballogic.model.request.UserRequest;
import org.hibernate.MappingException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserMapper {
    public static UserEntity mapper(UserRequest userRequest) {
        try {
            PhoneEntity phoneEntity = null;
            Role role = new Role("USER");
            List<Role> roleList = new ArrayList<>();
            roleList.add(role);
            if (userRequest.getPhone() != null) {
                phoneEntity = new PhoneEntity(userRequest.getPhone().getId(), userRequest.getPhone().getNumber(), userRequest.getPhone().getCityCode(), userRequest.getPhone().getCountryCode());
            }
            return new UserEntity(userRequest.getId(), userRequest.getName(), userRequest.getEmail(), userRequest.getPassword(), LocalDateTime.now(), null, true, phoneEntity, roleList);
        } catch (MappingException e) {
            throw new MappingException("Error to map User request.");
        }
    }
}
