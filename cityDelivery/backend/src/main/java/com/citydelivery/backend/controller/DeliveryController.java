package com.citydelivery.backend.controller;

import com.citydelivery.backend.dto.DeliveryDTO;
import com.citydelivery.backend.dto.LocationDTO;
import com.citydelivery.backend.mapper.DeliveryMapper;
import com.citydelivery.backend.model.Delivery;
import com.citydelivery.backend.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<DeliveryDTO> createDeliveryRequest(
            @Valid @RequestBody DeliveryDTO newDeliveryDTO, BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().build();
        Delivery newDelivery = DeliveryMapper.INSTANCE.toDelivery(newDeliveryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.save(newDelivery));
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
