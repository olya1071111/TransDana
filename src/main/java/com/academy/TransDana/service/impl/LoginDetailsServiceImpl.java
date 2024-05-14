package com.academy.TransDana.service.impl;

import com.academy.TransDana.model.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginDetailsServiceImpl implements UserDetailsService {

    private final LoginRepository loginRepository;
    private final static String USER_NOT_FOUND = "User not found!";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = loginRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        }
        return user;
    }

}
