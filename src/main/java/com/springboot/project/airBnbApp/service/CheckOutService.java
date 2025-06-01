package com.springboot.project.airBnbApp.service;

import com.springboot.project.airBnbApp.entity.Booking;

public interface CheckOutService {
    String checkoutSession(Booking booking, String successUrl, String failureUrl);
}
