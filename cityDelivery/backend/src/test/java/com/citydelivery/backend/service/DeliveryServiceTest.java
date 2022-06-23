package com.citydelivery.backend.service;

import com.citydelivery.backend.dto.DeliveryDTO;
import com.citydelivery.backend.dto.LocationDTO;
import com.citydelivery.backend.mapper.DeliveryMapper;
import com.citydelivery.backend.model.Courier;
import com.citydelivery.backend.model.Delivery;
import com.citydelivery.backend.model.Location;
import com.citydelivery.backend.repository.DeliveryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryService deliveryService;

    private Delivery assignedDelivery;

    private DeliveryDTO assignedDeliveryDTO;
    private Delivery unassignedDelivery;
    private Courier john;

    @BeforeEach
    public void setUp() {
        john = new Courier();
        john.setName("John Doe");
        john.setPassword("supersecret");
        john.setPassword("john@email.com");

        Location pickUp = new Location();
        pickUp.setLongitude(1.0);
        pickUp.setLatitude(2.0);
        pickUp.setId(1L);

        Location dropOff = new Location();
        dropOff.setLongitude(3.0);
        dropOff.setLatitude(4.0);
        dropOff.setId(2L);

        assignedDelivery = new Delivery();
        assignedDelivery.setId(1L);
        assignedDelivery.setPickup(pickUp);
        assignedDelivery.setDropOff(dropOff);
        assignedDelivery.setCourier(john);

        assignedDeliveryDTO = DeliveryMapper.INSTANCE.toDeliveryDTO(assignedDelivery);


        unassignedDelivery = new Delivery();
        unassignedDelivery.setId(2L);
        unassignedDelivery.setPickup(pickUp);
        unassignedDelivery.setDropOff(dropOff);

        lenient().when(deliveryRepository.getById(1L)).thenReturn(assignedDelivery);

        lenient().when(deliveryRepository.getDeliveriesBy(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(assignedDelivery, unassignedDelivery)));

        lenient().when(deliveryRepository.getAllByCourierIsNull(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(unassignedDelivery)));

        lenient().when(deliveryRepository.getAllByCourierIsNotNull(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(assignedDelivery)));

        lenient().when(deliveryRepository.getAllByCourier(eq(john), any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(assignedDelivery)));

        lenient().when(deliveryRepository.save(assignedDelivery)).thenReturn(assignedDelivery);
    }

    @Test
    void whenSaveNewDelivery_thenCallRepositorySaveOnceAndReturnSavedEntityAsDTO() {
        DeliveryDTO saved = deliveryService.save(assignedDelivery);

        DeliveryDTO expected = DeliveryMapper.INSTANCE.toDeliveryDTO(assignedDelivery);

        assertThat(saved).extracting(DeliveryDTO::getDropOff).extracting(LocationDTO::getLatitude)
                .isEqualTo(expected.getDropOff().getLatitude());
        assertThat(saved).extracting(DeliveryDTO::getDropOff).extracting(LocationDTO::getLongitude)
                .isEqualTo(expected.getDropOff().getLongitude());
        assertThat(saved).extracting(DeliveryDTO::getPickup).extracting(LocationDTO::getLatitude)
                .isEqualTo(expected.getPickup().getLatitude());
        assertThat(saved).extracting(DeliveryDTO::getPickup).extracting(LocationDTO::getLongitude)
                .isEqualTo(expected.getPickup().getLongitude());

        Mockito.verify(deliveryRepository, Mockito.times(1)).save(assignedDelivery);
    }
}
