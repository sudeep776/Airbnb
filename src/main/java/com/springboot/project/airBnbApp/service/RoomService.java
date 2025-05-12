package com.springboot.project.airBnbApp.service;

import com.springboot.project.airBnbApp.dto.HotelDto;
import com.springboot.project.airBnbApp.dto.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto createNewRoom(Long hotelId , RoomDto roomDto);
    List<RoomDto> getAllRoomsInHotel(Long hotelId);
    RoomDto getRoomById(Long roomId);
    RoomDto updateRoomById(Long roomId, RoomDto roomDto);
    void deleteRoomById(Long roomId);
}
