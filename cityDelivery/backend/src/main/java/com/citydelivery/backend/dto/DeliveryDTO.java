package com.citydelivery.backend.dto;

import javax.validation.Valid;
import java.time.LocalDateTime;

public class DeliveryDTO {
    private Long id;
    @Valid
    private LocationDTO pickup;
    private LocalDateTime pickupTime;
    @Valid
    private LocationDTO dropOff;
    private LocalDateTime deliveryTime;
    private CourierDTO courier;

    public DeliveryDTO() {}

    public DeliveryDTO(LocationDTO dropOff, LocationDTO pickup) {
        this.dropOff = dropOff;
        this.pickup = pickup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocationDTO getPickup() {
        return pickup;
    }

    public void setPickup(LocationDTO pickup) {
        this.pickup = pickup;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public LocationDTO getDropOff() {
        return dropOff;
    }

    public void setDropOff(LocationDTO dropOff) {
        this.dropOff = dropOff;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public CourierDTO getCourier() {
        return courier;
    }

    public void setCourier(CourierDTO courier) {
        this.courier = courier;
    }
}
