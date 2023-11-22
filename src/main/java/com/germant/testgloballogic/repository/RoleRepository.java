package com.germant.testgloballogic.repository;

import com.germant.testgloballogic.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> getRoleByName(String name);
}
