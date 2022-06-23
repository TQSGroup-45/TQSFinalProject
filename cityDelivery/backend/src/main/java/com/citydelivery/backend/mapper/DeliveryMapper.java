package com.citydelivery.backend.mapper;

import com.citydelivery.backend.dto.DeliveryDTO;
import com.citydelivery.backend.model.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = { CourierMapper.class, LocationMapper.class })
public interface DeliveryMapper {

    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

    Delivery toDelivery(DeliveryDTO dto);
    DeliveryDTO toDeliveryDTO(Delivery entity);

    List<Delivery> toDeliveries(List<DeliveryDTO> dtos);

    List<DeliveryDTO> toDeliveryDTOs(List<Delivery> entities);
}
