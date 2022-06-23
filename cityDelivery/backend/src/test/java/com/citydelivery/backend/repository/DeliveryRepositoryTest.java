package com.citydelivery.backend.repository;

import com.citydelivery.backend.model.Courier;
import com.citydelivery.backend.model.Delivery;
import com.citydelivery.backend.model.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class DeliveryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DeliveryRepository deliveryRepository;

    private Courier john;

    @BeforeEach
    void setUp() {
        john = new Courier();
        john.setName("John Doe");
        john.setPassword("supersecret");
        john.setPassword("john@email.com");
        entityManager.persistAndFlush(john);
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void whenFindDeliveryWithNoCourier_ThenReturnPageOfUnassignedDeliveries() {
        Delivery assignedDelivery = new Delivery();

        assignedDelivery.setPickup(new Location());
        assignedDelivery.setDropOff(new Location());
        assignedDelivery.setCourier(john);
        assignedDelivery.setDeliveryTime(LocalDateTime.now());

        entityManager.persistAndFlush(assignedDelivery);


        Delivery unassignedDelivery = new Delivery();

        unassignedDelivery.setPickup(new Location());
        unassignedDelivery.setDropOff(new Location());

        entityManager.persistAndFlush(unassignedDelivery);


        Page<Delivery> foundUnassignedDeliveries = deliveryRepository.getAllByCourierIsNull(PageRequest.of(0, 2));

        assertThat(foundUnassignedDeliveries.getTotalElements()).isEqualTo(1);

        Delivery foundUnassignedDelivery = foundUnassignedDeliveries.getContent().get(0);
        assertThat(foundUnassignedDelivery).isEqualTo(unassignedDelivery);
    }

    @Test
    void whenFindDeliveryWithCourier_ThenReturnPageOfUnassignedDeliveries() {
        Delivery assignedDelivery = new Delivery();

        assignedDelivery.setPickup(new Location());
        assignedDelivery.setDropOff(new Location());
        assignedDelivery.setCourier(john);
        assignedDelivery.setDeliveryTime(LocalDateTime.now());

        entityManager.persistAndFlush(assignedDelivery);


        Delivery unassignedDelivery = new Delivery();

        unassignedDelivery.setPickup(new Location());
        unassignedDelivery.setDropOff(new Location());

        entityManager.persistAndFlush(unassignedDelivery);


        Page<Delivery> foundUnassignedDeliveries = deliveryRepository.getAllByCourierIsNotNull(PageRequest.of(0, 2));

        assertThat(foundUnassignedDeliveries.getTotalElements()).isEqualTo(1);

        Delivery foundUnassignedDelivery = foundUnassignedDeliveries.getContent().get(0);
        assertThat(foundUnassignedDelivery).isEqualTo(assignedDelivery);
    }

    @Test
    void whenFindDeliveriesOfJohn_ThenReturnPageOfJohnDeliveries() {
        Delivery assignedDelivery = new Delivery();

        assignedDelivery.setPickup(new Location());
        assignedDelivery.setDropOff(new Location());
        assignedDelivery.setCourier(john);
        assignedDelivery.setDeliveryTime(LocalDateTime.now());

        entityManager.persistAndFlush(assignedDelivery);


        Delivery unassignedDelivery = new Delivery();

        unassignedDelivery.setPickup(new Location());
        unassignedDelivery.setDropOff(new Location());

        entityManager.persistAndFlush(unassignedDelivery);


        Page<Delivery> foundUnassignedDeliveries = deliveryRepository.getAllByCourier(john, PageRequest.of(0, 2));

        assertThat(foundUnassignedDeliveries.getTotalElements()).isEqualTo(1);

        Delivery foundUnassignedDelivery = foundUnassignedDeliveries.getContent().get(0);
        assertThat(foundUnassignedDelivery).isEqualTo(assignedDelivery);
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Delivery fromDb = deliveryRepository.findById(-45L).orElse(null);
        assertThat(fromDb).isNull();
    }
}
