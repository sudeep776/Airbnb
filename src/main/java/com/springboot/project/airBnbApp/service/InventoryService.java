package com.springboot.project.airBnbApp.service;

import com.springboot.project.airBnbApp.entity.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteFutureInventories(Room room);
}
