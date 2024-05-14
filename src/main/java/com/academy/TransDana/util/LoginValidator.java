package com.academy.TransDana.util;

import com.academy.TransDana.model.domain.Login;
import com.academy.TransDana.service.impl.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginValidator implements Validator {

    private final LoginService loginService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Login.class.equals(clazz);
    }


    /** validate() check if the username is in the database, then we return an error
     * if username is exists in database then return errors = 1:
     * if username is not exists in database then errors = 0;
     */
    @Override
    public void validate(Object target, Errors errors) {

        Login login = (Login) target;

        try {
            Optional.of(loginService.isUsernameExists(login.getUsername()));

        } catch (NullPointerException e) {
            return;
        }
        errors.rejectValue("username","","Username is exists!");
    }
}

