package com.citydelivery.backend.mapper;

import com.citydelivery.backend.dto.LocationDTO;
import com.citydelivery.backend.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    Location toLocation(LocationDTO dto);
    LocationDTO toLocationDTO(Location entity);
}
