package com.citydelivery.backend.controller;

import com.citydelivery.backend.JsonUtils;
import com.citydelivery.backend.dto.DeliveryDTO;
import com.citydelivery.backend.dto.LocationDTO;
import com.citydelivery.backend.service.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeliveryController.class)
class DeliveryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DeliveryService deliveryService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostDelivery_ThenCreateDelivery() throws Exception {
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setPickup(new LocationDTO(-1.0, 0.0));
        deliveryDTO.setDropOff(new LocationDTO(1.0, 2.0));

        when(deliveryService.save(any())).thenReturn(deliveryDTO);

        mvc.perform(
                post("/api/deliveries").contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.toJson(deliveryDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.pickup.latitude", is(-1.0)))
                .andExpect(jsonPath("$.pickup.longitude", is(0.0)))
                .andExpect(jsonPath("$.dropOff.latitude", is(1.0)))
                .andExpect(jsonPath("$.dropOff.longitude", is(2.0)));

        verify(deliveryService, times(1)).save(any());
    }

}