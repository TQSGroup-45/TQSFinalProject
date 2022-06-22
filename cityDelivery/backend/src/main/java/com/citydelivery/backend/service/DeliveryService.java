package com.citydelivery.backend.service;

import com.citydelivery.backend.dto.DeliveryDTO;
import com.citydelivery.backend.model.Courier;
import com.citydelivery.backend.model.Delivery;
import com.citydelivery.backend.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public DeliveryDTO save(Delivery delivery) {
        return new DeliveryDTO();
    }

    public Page<DeliveryDTO> getPageOfDeliveries(Pageable pageable) {
        return Page.empty();
    }

    public Page<DeliveryDTO> getPageOfAvailableDeliveries(Pageable pageable) {
        return Page.empty();
    }

    public Page<DeliveryDTO> getPageOfAssignedDeliveries(Pageable pageable) {
        return Page.empty();
    }

    public Page<DeliveryDTO> getPageOfDeliveriesForCourier(Courier courier, Pageable pageable) {
        return Page.empty();
    }

}
