package com.citydelivery.backend.repository;

import com.citydelivery.backend.model.Courier;
import com.citydelivery.backend.model.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Page<Delivery> getDeliveriesBy(Pageable pageable);
    Page<Delivery> getAllByCourierIsNull(Pageable pageable);

    Page<Delivery> getAllByCourierIsNotNull(Pageable pageable);

    Page<Delivery> getAllByCourier(Courier courier, Pageable pageable);


}