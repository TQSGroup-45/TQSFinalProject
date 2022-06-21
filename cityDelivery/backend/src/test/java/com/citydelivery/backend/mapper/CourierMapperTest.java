package com.citydelivery.backend.mapper;

import com.citydelivery.backend.dto.CourierDTO;
import com.citydelivery.backend.model.Courier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourierMapperTest {

    @Test
    void whenConvertCourierEntityToCourierDto_thenCorrect() {
        Courier courier = new Courier();
        courier.setId(1L);
        courier.setEmail("email");
        courier.setName("name");
        courier.setPassword("password");

        CourierDTO courierDTO = CourierMapper.INSTANCE.toCourierDTO(courier);
        assertEquals(courier.getId(), courierDTO.getId());
        assertEquals(courier.getEmail(), courierDTO.getEmail());
        assertEquals(courier.getName(), courierDTO.getName());
        assertEquals(courier.getPassword(), courierDTO.getPassword());
    }


    @Test
    void whenConvertCourierDtoToEntity_thenCorrect() {
        CourierDTO courierDTO = new CourierDTO();
        courierDTO.setId(1L);
        courierDTO.setEmail("email");
        courierDTO.setName("name");

        Courier courier = CourierMapper.INSTANCE.toCourier(courierDTO);
        assertEquals(courierDTO.getId(), courier.getId());
        assertEquals(courierDTO.getEmail(), courier.getEmail());
        assertEquals(courierDTO.getName(), courier.getName());
        assertEquals(courierDTO.getPassword(), courier.getPassword());
    }


}