package com.academy.TransDana.service.impl;

import com.academy.TransDana.dto.LoginDto;
import com.academy.TransDana.dto.UserDto;
import com.academy.TransDana.model.domain.Login;
import com.academy.TransDana.model.domain.User;
import com.academy.TransDana.model.mapping.LoginMapper;
import com.academy.TransDana.model.mapping.UserMapper;
import com.academy.TransDana.model.repository.LoginRepository;
import com.academy.TransDana.model.repository.UserRepository;
import com.academy.TransDana.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final LoginRepository loginRepository;
    private final LoginMapper loginMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<UserDto> getAll() {
        var users = userRepository.findAll();
        return userMapper.map(users);
    }

    /**
     * returns all data from the login table
     *
     * @return List<LoginDto>
     */
    @Override
    public List<LoginDto> getAllLogin() {
        var logins = loginRepository.findAll();
        return loginMapper.map(logins);
    }

    public List<LoginDto> getAllLoginNotBlocked(String role) {
        var logins = loginRepository.findByRoleNotLike(role);
        return loginMapper.map(logins);
    }



    /**
     * Search for all users depending on the role
     *
     * @param role
     * @return List<LoginDto>
     */
    @Override
    public List<LoginDto> getAllLogin(String role) {
        var logins = loginRepository.findByRole(role);
        return loginMapper.map(logins);
    }


    /**
     * Add new user in database
     *
     * @param userDto
     */
    @Override
    @Transactional
    public void saveUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);
    }

    /**
     * Adding data to the table Login
     *
     * @param login
     * @param userDto
     */
    @Override
    @Transactional
    public void saveLogin(Login login, UserDto userDto) {

        login.setPassword(passwordEncoder.encode(login.getPassword()));
        login.setEnabled(true);
        login.setCredentialsNonExpired(true);
        login.setAccountNonExpired(true);
        login.setAccountNonLocked(true);

        User user = userMapper.toEntity(userDto);
        login.setUser(searchUser(user));

        loginRepository.save(login);
    }

    /**
     * looking for a user by name
     *
     * @param user
     * @return user
     */
    public User searchUser(User user) {

        return userRepository.findByName(user.getName());
    }

    @Override
    @Transactional
    public void editRoleLogin(Integer id, String role) {

        loginRepository.updateRouteStatus(id, role);
    }
}
