package com.citydelivery.backend.mapper;

import com.citydelivery.backend.dto.DeliveryDTO;
import com.citydelivery.backend.model.Delivery;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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


    @Test
    void whenConvertDeliveryDTOListToEntityList_thenCorrect() {
        DeliveryDTO deliveryDTOA = new DeliveryDTO();
        deliveryDTOA.setId(1L);
        deliveryDTOA.setPickupTime(LocalDateTime.now());
        deliveryDTOA.setDeliveryTime(LocalDateTime.now());

        DeliveryDTO deliveryDTOB = new DeliveryDTO();
        deliveryDTOB.setId(2L);
        deliveryDTOB.setPickupTime(LocalDateTime.now());
        deliveryDTOB.setDeliveryTime(LocalDateTime.now());

        List<Delivery> mappedDeliveries = DeliveryMapper.INSTANCE.toDeliveries(Arrays.asList(deliveryDTOA, deliveryDTOB));

        assertThat( mappedDeliveries.get(0).getId() ).isEqualTo(deliveryDTOA.getId());
        assertThat( mappedDeliveries.get(0).getDeliveryTime() ).isEqualTo(deliveryDTOA.getDeliveryTime());
        assertThat( mappedDeliveries.get(0).getPickupTime() ).isEqualTo(deliveryDTOA.getPickupTime());
    }


    @Test
    void whenConvertDeliveryListToDTOList_thenCorrect() {
        Delivery deliveryA = new Delivery();
        deliveryA.setId(1L);
        deliveryA.setPickupTime(LocalDateTime.now());
        deliveryA.setDeliveryTime(LocalDateTime.now());

        Delivery deliveryB = new Delivery();
        deliveryB.setId(2L);
        deliveryB.setPickupTime(LocalDateTime.now());
        deliveryB.setDeliveryTime(LocalDateTime.now());

        List<DeliveryDTO> mappedDeliveries = DeliveryMapper.INSTANCE.toDeliveryDTOs(Arrays.asList(deliveryA, deliveryB));

        assertThat( mappedDeliveries.get(0).getId() ).isEqualTo(deliveryA.getId());
        assertThat( mappedDeliveries.get(0).getDeliveryTime() ).isEqualTo(deliveryA.getDeliveryTime());
        assertThat( mappedDeliveries.get(0).getPickupTime() ).isEqualTo(deliveryA.getPickupTime());
    }

}