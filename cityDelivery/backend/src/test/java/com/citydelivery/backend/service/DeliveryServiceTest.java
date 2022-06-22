package com.citydelivery.backend.service;

import com.citydelivery.backend.dto.DeliveryDTO;
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

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private DeliveryService deliveryService;

    private Delivery assignedDelivery;
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

        unassignedDelivery = new Delivery();
        unassignedDelivery.setId(2L);
        unassignedDelivery.setPickup(pickUp);
        unassignedDelivery.setDropOff(dropOff);

        Mockito.when(deliveryRepository.getById(1L)).thenReturn(assignedDelivery);

        Mockito.when(deliveryRepository.getDeliveriesBy(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(assignedDelivery, unassignedDelivery)));

        Mockito.when(deliveryRepository.getAllByCourierIsNull(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(unassignedDelivery)));

        Mockito.when(deliveryRepository.getAllByCourierIsNotNull(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(assignedDelivery)));

        Mockito.when(deliveryRepository.getAllByCourier(eq(john), any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(assignedDelivery)));

        Mockito.when(deliveryRepository.save(any())).thenReturn(new Delivery());
    }

    @Test
    void whenGetDeliveries_thenReturn2RecordsInPage() {
        Page<DeliveryDTO> foundDeliveries =
                deliveryService.getPageOfDeliveries(PageRequest.of(0, 2, Sort.by("id")));

        assertThat(foundDeliveries.getTotalElements()).isEqualTo(2);

        DeliveryDTO found = foundDeliveries.getContent().get(0);
        DeliveryDTO expected = DeliveryMapper.INSTANCE.toDeliveryDTO(unassignedDelivery);

        assertThat(found).isEqualTo(expected);
        assertThat(foundDeliveries.getContent().get(1).getId()).isEqualTo(unassignedDelivery.getId());

        Mockito.verify(deliveryRepository, Mockito.times(1)).getDeliveriesBy(any());
    }

    @Test
    void whenGetAvailableDeliveries_thenReturn1RecordInPage() {
        Page<DeliveryDTO> foundDeliveries =
                deliveryService.getPageOfAvailableDeliveries(PageRequest.of(0, 2, Sort.by("id")));

        assertThat(foundDeliveries.getTotalElements()).isEqualTo(1);

        DeliveryDTO found = foundDeliveries.getContent().get(0);
        DeliveryDTO expected = DeliveryMapper.INSTANCE.toDeliveryDTO(unassignedDelivery);

        assertThat(found).isEqualTo(expected);
    }

    @Test
    void whenGetAssignedDeliveries_thenReturn1RecordInPage() {
        Page<DeliveryDTO> foundDeliveries =
                deliveryService.getPageOfAssignedDeliveries(PageRequest.of(0, 2, Sort.by("id")));

        assertThat(foundDeliveries.getTotalElements()).isEqualTo(1);

        DeliveryDTO found = foundDeliveries.getContent().get(0);
        DeliveryDTO expected = DeliveryMapper.INSTANCE.toDeliveryDTO(assignedDelivery);

        assertThat(found).isEqualTo(expected);
    }

    @Test
    void whenGetDeliveriesAssignedToJohn_thenReturn1RecordInPage() {
        Page<DeliveryDTO> foundDeliveries =
                deliveryService.getPageOfDeliveriesForCourier(john, PageRequest.of(0, 2, Sort.by("id")));

        assertThat(foundDeliveries.getTotalElements()).isEqualTo(1);

        DeliveryDTO found = foundDeliveries.getContent().get(0);
        DeliveryDTO expected = DeliveryMapper.INSTANCE.toDeliveryDTO(assignedDelivery);

        assertThat(found).isEqualTo(expected);
    }

    @Test
    void whenSaveNewDelivery_thenCallRepositorySaveOnceAndReturnSavedEntityAsDTO() {
        DeliveryDTO saved = deliveryService.save(assignedDelivery);

        DeliveryDTO expected = DeliveryMapper.INSTANCE.toDeliveryDTO(assignedDelivery);

        assertThat(saved).isEqualTo(expected);

        Mockito.verify(deliveryRepository, Mockito.times(1)).save(assignedDelivery);
    }
}
