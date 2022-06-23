package com.citydelivery.backend;

import com.citydelivery.backend.dto.DeliveryDTO;
import com.citydelivery.backend.dto.LocationDTO;
import com.citydelivery.backend.model.Delivery;
import com.citydelivery.backend.model.Location;
import com.citydelivery.backend.repository.DeliveryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class DeliveryRestControllerTemplateIT {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DeliveryRepository repository;

    @AfterEach
    public void resetDb(){
        repository.deleteAll();
    }

    @Test
    void whenValidInput_thenCreateDelivery() {
        DeliveryDTO delivery = new DeliveryDTO();

        delivery.setPickup(new LocationDTO(1D, 2D));
        delivery.setDropOff(new LocationDTO(3D, 4D));

        ResponseEntity<DeliveryDTO> entity = restTemplate
                .postForEntity("/api/deliveries", delivery, DeliveryDTO.class);

        List<Delivery> found = repository.findAll();

        assertAll(() -> {
            assertThat(found).extracting(Delivery::getPickup).extracting(Location::getLatitude).containsOnly(1D);
            assertThat(found).extracting(Delivery::getPickup).extracting(Location::getLongitude).containsOnly(2D);
            assertThat(found).extracting(Delivery::getDropOff).extracting(Location::getLatitude).containsOnly(3D);
            assertThat(found).extracting(Delivery::getDropOff).extracting(Location::getLongitude).containsOnly(4D);
        });
    }

    @Test
    void whenInvalidInput_thenNoEntitiesCreated() {
        DeliveryDTO delivery = new DeliveryDTO();

        delivery.setPickup(new LocationDTO(1D, 2D));

        ResponseEntity<DeliveryDTO> entity = restTemplate
                .postForEntity("/api/deliveries", delivery, DeliveryDTO.class);

        List<Delivery> found = repository.findAll();

        assertThat(found).isEmpty();
    }

}
