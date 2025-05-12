package com.springboot.project.airBnbApp.dto;

import com.springboot.project.airBnbApp.entity.HotelContactInfo;
import com.springboot.project.airBnbApp.entity.Room;
import com.springboot.project.airBnbApp.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class HotelDto {
    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;
}
