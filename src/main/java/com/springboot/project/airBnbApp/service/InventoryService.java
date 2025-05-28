package com.springboot.project.airBnbApp.service;

import com.springboot.project.airBnbApp.dto.HotelDto;
import com.springboot.project.airBnbApp.dto.HotelPriceDto;
import com.springboot.project.airBnbApp.dto.HotelSearchRequest;
import com.springboot.project.airBnbApp.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
