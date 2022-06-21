package com.citydelivery.backend.repository;

import com.citydelivery.backend.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}