package com.academy.TransDana.service.impl;

import com.academy.TransDana.model.domain.Login;
import com.academy.TransDana.model.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService  {

    private final LoginRepository loginRepository;
    private final static String USER_NOT_FOUND = "User not found!";

    /** method isUsernameExists checks whether this registered user is in the database
     * @param username
     * @return Optional<Login>
     */

    public Login isUsernameExists(String username) throws UsernameNotFoundException {

        Login login = loginRepository.findByUsername(username);

        return login;
    }
}