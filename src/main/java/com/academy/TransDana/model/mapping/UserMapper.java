package com.academy.TransDana.model.mapping;

import com.academy.TransDana.model.domain.User;
import com.academy.TransDana.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDTO(User user);

    User toEntity(UserDto userDto);

    List<UserDto> map(List<User> users);
}
