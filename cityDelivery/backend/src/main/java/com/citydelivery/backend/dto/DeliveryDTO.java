package com.citydelivery.backend.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class DeliveryDTO {
    private Long id;
    @Valid
    @NotNull
    private LocationDTO pickup;
    private LocalDateTime pickupTime;
    @Valid
    @NotNull
    private LocationDTO dropOff;
    private LocalDateTime deliveryTime;
    private CourierDTO courier;

    private LocalDateTime submissionDateTime;

    public DeliveryDTO() {
        submissionDateTime = LocalDateTime.now();
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

    public LocalDateTime getSubmissionDateTime() {
        return submissionDateTime;
    }

    public void setSubmissionDateTime(LocalDateTime submissionDateTime) {
        this.submissionDateTime = submissionDateTime;
    }
}
