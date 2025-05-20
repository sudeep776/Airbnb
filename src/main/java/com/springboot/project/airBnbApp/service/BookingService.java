package com.springboot.project.airBnbApp.service;

import com.springboot.project.airBnbApp.dto.BookingDto;
import com.springboot.project.airBnbApp.dto.BookingRequest;
import com.springboot.project.airBnbApp.dto.GuestDto;

import java.util.List;

public interface BookingService {

    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
