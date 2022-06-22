package com.citydelivery.backend.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void whenCalculateDistanceBetweenLocations_thenCorrect() {
        Location locationA = new Location(1d, 1d);
        Location locationB = new Location(5d, 4d);

        Double calculatedDistance = locationA.distanceToLocation(locationB);
        assertThat( calculatedDistance ).isEqualTo(5d);
    }
}