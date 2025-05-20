package com.springboot.project.airBnbApp.service;

import com.springboot.project.airBnbApp.dto.HotelDto;
import com.springboot.project.airBnbApp.dto.HotelInfoDto;
import com.springboot.project.airBnbApp.entity.Hotel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto) ;

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id,HotelDto hotelDto);

    Boolean deleteHotelById(Long id);

    void activateHotel(Long id);

    HotelInfoDto getHotelInfoById(Long hotelId);
}
