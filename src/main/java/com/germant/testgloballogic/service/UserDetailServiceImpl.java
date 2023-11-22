package com.germant.testgloballogic.service;

import com.germant.testgloballogic.model.Role;
import com.germant.testgloballogic.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserServiceImpl userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity;
        try {
            userEntity = this.userService.findUserByEmail(email);
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), mapRole(userEntity.getRoleList()));
    }

    private Collection<? extends GrantedAuthority> mapRole(List<Role> roleList) {
        return roleList.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
