package com.citydelivery.backend.mapper;

import com.citydelivery.backend.dto.LocationDTO;
import com.citydelivery.backend.model.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationMapperTest {

    @Test
    void whenConvertLocationEntityToLocationDto_thenCorrect() {
        Location location = new Location();
        location.setId(1L);
        location.setLatitude(2.0);
        location.setLongitude(-3.9);

        LocationDTO locationDTO = LocationMapper.INSTANCE.toLocationDTO(location);
        assertEquals(location.getId(), locationDTO.getId());
        assertEquals(location.getLatitude(), locationDTO.getLatitude());
        assertEquals(location.getLongitude(), locationDTO.getLongitude());
    }


    @Test
    void whenConvertLocationDtoToEntity_thenCorrect() {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(1L);
        locationDTO.setLatitude(2.0);
        locationDTO.setLongitude(-3.9);

        Location location = LocationMapper.INSTANCE.toLocation(locationDTO);
        assertEquals(locationDTO.getId(), location.getId());
        assertEquals(locationDTO.getLatitude(), location.getLatitude());
        assertEquals(locationDTO.getLongitude(), location.getLongitude());
    }


}