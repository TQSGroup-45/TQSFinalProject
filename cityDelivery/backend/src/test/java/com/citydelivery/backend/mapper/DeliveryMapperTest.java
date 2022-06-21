package com.citydelivery.backend.mapper;

import com.citydelivery.backend.dto.DeliveryDTO;
import com.citydelivery.backend.model.Delivery;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryMapperTest {

    @Test
    void whenConvertDeliveryEntityToDeliveryDto_thenCorrect() {
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        delivery.setPickupTime(LocalDateTime.now());
        delivery.setDeliveryTime(LocalDateTime.now());

        DeliveryDTO deliveryDTO = DeliveryMapper.INSTANCE.toDeliveryDTO(delivery);
        assertEquals(delivery.getId(), deliveryDTO.getId());
        assertEquals(delivery.getPickupTime(), deliveryDTO.getPickupTime());
        assertEquals(delivery.getDeliveryTime(), deliveryDTO.getDeliveryTime());
    }


    @Test
    void whenConvertDeliveryDtoToEntity_thenCorrect() {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setId(1L);
        deliveryDTO.setPickupTime(LocalDateTime.now());
        deliveryDTO.setDeliveryTime(LocalDateTime.now());

        Delivery delivery = DeliveryMapper.INSTANCE.toDelivery(deliveryDTO);
        assertEquals(deliveryDTO.getId(), delivery.getId());
        assertEquals(deliveryDTO.getPickupTime(), delivery.getPickupTime());
        assertEquals(deliveryDTO.getDeliveryTime(), delivery.getDeliveryTime());
    }


}