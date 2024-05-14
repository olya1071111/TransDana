package com.academy.TransDana.model.mapping;

import com.academy.TransDana.dto.RouteDto;
import com.academy.TransDana.model.domain.Route;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    RouteDto toDTO(Route route);

    Route toEntity(RouteDto routeDto);

    List<RouteDto> map(List<Route> routes);

}
