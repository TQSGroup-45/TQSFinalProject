package com.citydelivery.backend.controller;

import com.citydelivery.backend.dto.DeliveryDTO;
import com.citydelivery.backend.dto.LocationDTO;
import com.citydelivery.backend.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<DeliveryDTO> createDeliveryRequest(@RequestBody DeliveryDTO newDelivery) {
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<Page<DeliveryDTO>> getDeliveryRequests(
            @RequestBody Optional<LocationDTO> optionalLocationDTO,
            @RequestParam Optional<Double> maxDistance,
            @RequestParam(name = "assigned") Optional<Boolean> isAssigned,
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<Integer> size
    ) {
        return ResponseEntity.accepted().build();
    }
}
