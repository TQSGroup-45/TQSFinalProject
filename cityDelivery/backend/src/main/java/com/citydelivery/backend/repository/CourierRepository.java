package com.citydelivery.backend.repository;

import com.citydelivery.backend.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier, Long> {
}