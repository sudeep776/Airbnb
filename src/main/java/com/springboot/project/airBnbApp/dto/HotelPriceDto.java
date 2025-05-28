package com.springboot.project.airBnbApp.dto;

import com.springboot.project.airBnbApp.entity.Hotel;
import lombok.Data;

@Data
public class HotelPriceDto {
    private Hotel hotel;
    private Double price;
}
