package com.academy.TransDana.service;

import com.academy.TransDana.dto.LoginDto;
import com.academy.TransDana.dto.UserDto;
import com.academy.TransDana.model.domain.Login;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    public List<LoginDto> getAllLogin();

    public List<LoginDto> getAllLoginNotBlocked(String role);

    public List<LoginDto> getAllLogin(String role);

    public void saveUser(UserDto user);

    public void saveLogin(Login login, UserDto user);

    public void editRoleLogin(Integer id, String role);
}
