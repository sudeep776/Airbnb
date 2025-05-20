package com.springboot.project.airBnbApp.service.impl;

import com.springboot.project.airBnbApp.dto.RoomDto;
import com.springboot.project.airBnbApp.entity.Hotel;
import com.springboot.project.airBnbApp.entity.Room;
import com.springboot.project.airBnbApp.exception.ResourceNotFoundException;
import com.springboot.project.airBnbApp.repository.HotelRepository;
import com.springboot.project.airBnbApp.repository.RoomRepository;
import com.springboot.project.airBnbApp.service.InventoryService;
import com.springboot.project.airBnbApp.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Override
    public RoomDto createNewRoom(Long hotelId , RoomDto roomDto) {
        log.info("Creating new room with hotel id: {}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id : "+hotelId));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        if(hotel.getActive()){
            inventoryService.initializeRoomForAYear(room);
        }

        return modelMapper.map(room,RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Get all rooms by id :{}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id : "+hotelId));
        return hotel.getRooms().stream().map(room->modelMapper.map(room,RoomDto.class)).toList();
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting room by id : {}",roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(()->new ResourceNotFoundException("Room not found with id : "+roomId));
        return modelMapper.map(room,RoomDto.class);
    }

    @Override
    public RoomDto updateRoomById(Long roomId,RoomDto roomDto) {
        log.info("Updating room with id : {}",roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(()->new ResourceNotFoundException("Room not found with id : "+roomId));
        modelMapper.map(roomDto,room);
        Room roomSaved =roomRepository.save(room);
        return modelMapper.map(roomSaved,RoomDto.class);
    }


    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room with id : {}",roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(()->new ResourceNotFoundException("Room not found with id : "+roomId));
        inventoryService.deleteAllInventories(room);
        roomRepository.deleteById(roomId);
        //TODO : to be checked
    }
}
