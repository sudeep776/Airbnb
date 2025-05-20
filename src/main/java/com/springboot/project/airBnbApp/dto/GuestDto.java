package com.springboot.project.airBnbApp.dto;

import com.springboot.project.airBnbApp.entity.Booking;
import com.springboot.project.airBnbApp.entity.User;
import com.springboot.project.airBnbApp.entity.enums.Gender;
import jakarta.persistence.*;

import java.util.Set;

public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
    private Set<Booking> bookings;
}
