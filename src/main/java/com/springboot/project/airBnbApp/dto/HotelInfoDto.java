package com.springboot.project.airBnbApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HotelInfoDto {
    private HotelDto hotelDto;
    private List<RoomDto> rooms;
}
