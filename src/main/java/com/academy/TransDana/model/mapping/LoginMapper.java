package com.academy.TransDana.model.mapping;

import com.academy.TransDana.dto.LoginDto;
import com.academy.TransDana.model.domain.Login;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    LoginDto toDTO(Login login);

    Login toEntity(LoginDto loginDto);

    List<LoginDto> map(List<Login> logins);
}
