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

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public LocationDTO getDropOff() {
        return dropOff;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDropOff(LocationDTO dropOff) {
        this.dropOff = dropOff;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setPickup(LocationDTO pickup) {
        this.pickup = pickup;
    }

    public LocationDTO getPickup() {
        return pickup;
    }

    public void setCourier(CourierDTO courier) {
        this.courier = courier;
    }

    public CourierDTO getCourier() {
        return courier;
    }

    public LocalDateTime getSubmissionDateTime() {
        return submissionDateTime;
    }

    public void setSubmissionDateTime(LocalDateTime submissionDateTime) {
        this.submissionDateTime = submissionDateTime;
    }
}
