package com.springboot.project.airBnbApp.repository;

import com.springboot.project.airBnbApp.entity.Inventory;
import com.springboot.project.airBnbApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    void deleteByDateAfter(LocalDate date, Room room);
}
