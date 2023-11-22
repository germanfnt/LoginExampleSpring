package com.germant.testgloballogic.mapper;

import com.germant.testgloballogic.model.PhoneEntity;
import com.germant.testgloballogic.model.UserEntity;
import com.germant.testgloballogic.model.request.PhoneRequest;
import com.germant.testgloballogic.model.response.LoginResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LoginMapperTest {

    @Test
    void testMapper() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("asdewrrwesdfhfg");
        userEntity.setDateRegister(LocalDateTime.of(2023, 1, 1, 12, 0));
        userEntity.setLastAccess(LocalDateTime.of(2023, 1, 2, 12, 0));
        userEntity.setEmail("german@test.com");
        userEntity.setActive(true);
        userEntity.setName("German Torres");
        userEntity.setPassword("hashedPassword");

        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setId(1L);
        phoneEntity.setNumber(132123L);
        phoneEntity.setCityCode(1);
        phoneEntity.setCountryCode("456");

        userEntity.setPhoneEntity(phoneEntity);

        LoginResponse expectedResponse = new LoginResponse(
                "asdewrrwesdfhfg",
                LocalDateTime.of(2023, 1, 1, 12, 0),
                LocalDateTime.of(2023, 1, 2, 12, 0),
                "token",
                true,
                "German Torres",
                "german@test.com",
                "hashedPassword",
                new PhoneRequest(1L, 132123L, 1, "456")
        );

        LoginResponse actualResponse = LoginMapper.mapper(userEntity);

        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getCreated(), actualResponse.getCreated());
        assertEquals(expectedResponse.getLastAcces(), actualResponse.getLastAcces());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
        assertEquals(expectedResponse.isActive(), actualResponse.isActive());
        assertEquals(expectedResponse.getName(), actualResponse.getName());
        assertEquals(expectedResponse.getPassword(), actualResponse.getPassword());

        PhoneRequest expectedPhoneRequest = expectedResponse.getPhone();
        PhoneRequest actualPhoneRequest = actualResponse.getPhone();

        assertEquals(expectedPhoneRequest.getId(), actualPhoneRequest.getId());
        assertEquals(expectedPhoneRequest.getNumber(), actualPhoneRequest.getNumber());
        assertEquals(expectedPhoneRequest.getCityCode(), actualPhoneRequest.getCityCode());
        assertEquals(expectedPhoneRequest.getCountryCode(), actualPhoneRequest.getCountryCode());
    }
}