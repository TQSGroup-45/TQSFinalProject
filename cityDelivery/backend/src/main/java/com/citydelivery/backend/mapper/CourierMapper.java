package com.citydelivery.backend.mapper;

import com.citydelivery.backend.dto.CourierDTO;
import com.citydelivery.backend.model.Courier;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = DeliveryMapper.class)
public interface CourierMapper {

    CourierMapper INSTANCE = Mappers.getMapper(CourierMapper.class);

    Courier toCourier(CourierDTO dto);

    CourierDTO toCourierDTO(Courier entity);
}
