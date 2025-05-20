package com.springboot.project.airBnbApp.dto;

import com.springboot.project.airBnbApp.entity.Guest;
import com.springboot.project.airBnbApp.entity.Hotel;
import com.springboot.project.airBnbApp.entity.Room;
import com.springboot.project.airBnbApp.entity.User;
import com.springboot.project.airBnbApp.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private User user;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Boolean active;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
}
