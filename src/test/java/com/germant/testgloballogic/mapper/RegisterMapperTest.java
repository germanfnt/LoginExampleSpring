package com.germant.testgloballogic.mapper;

import com.germant.testgloballogic.model.UserEntity;
import com.germant.testgloballogic.model.response.RegisterResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RegisterMapperTest {

    @Test
    void testMapper() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("ASE123W32");
        userEntity.setDateRegister(LocalDateTime.of(2023, 1, 1, 12, 0));
        userEntity.setLastAccess(LocalDateTime.of(2023, 1, 2, 12, 0));
        userEntity.setActive(true);

        RegisterResponse expectedResponse = new RegisterResponse("ASE123W32", LocalDateTime.of(2023, 1, 1, 12, 0), LocalDateTime.of(2023, 1, 2, 12, 0), null, true);

        RegisterResponse actualResponse = RegisterMapper.mapper(userEntity);

        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getCreated(), actualResponse.getCreated());
        assertEquals(expectedResponse.getLastLogin(), actualResponse.getLastLogin());
        assertEquals(expectedResponse.isActive(), actualResponse.isActive());
    }

}