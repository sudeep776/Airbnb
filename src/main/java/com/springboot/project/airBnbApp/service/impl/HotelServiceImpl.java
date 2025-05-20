package com.springboot.project.airBnbApp.service.impl;

import com.springboot.project.airBnbApp.dto.HotelDto;
import com.springboot.project.airBnbApp.dto.HotelInfoDto;
import com.springboot.project.airBnbApp.dto.RoomDto;
import com.springboot.project.airBnbApp.entity.Hotel;
import com.springboot.project.airBnbApp.entity.Room;
import com.springboot.project.airBnbApp.exception.ResourceNotFoundException;
import com.springboot.project.airBnbApp.repository.HotelRepository;
import com.springboot.project.airBnbApp.repository.RoomRepository;
import com.springboot.project.airBnbApp.service.HotelService;
import com.springboot.project.airBnbApp.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final RoomRepository roomRepository;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name : {}",hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto,Hotel.class);
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("Created a new hotel with id : {}",hotelDto.getId());
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting hotel with id : {}",id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id: " +id));
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating hotel with id : {}",id);
        Hotel hotelToUpdate = hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel not found with id :"+id));
        modelMapper.map(hotelDto,hotelToUpdate);
        Hotel hotel = hotelRepository.save(hotelToUpdate);
        return modelMapper.map(hotel,HotelDto.class);

    }

    @Override
    @Transactional
    public Boolean deleteHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id: "+id));
        for(Room room : hotel.getRooms()){
            inventoryService.deleteAllInventories(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);
        return true;

    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId){
        log.info("Activating the hotel : {}",hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id: "+hotelId));
        if(hotel.getActive()==false) {
            hotel.setActive(true);
            for(Room room : hotel.getRooms()){
                inventoryService.initializeRoomForAYear(room);
            }
            hotelRepository.save(hotel);
        }
        else return;
    }

    @Override
    public HotelInfoDto getHotelInfoById(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with id : "+hotelId));

        List<RoomDto> rooms = hotel.getRooms().stream().map(room->modelMapper.map(room,RoomDto.class)).toList();
        return new HotelInfoDto(modelMapper.map(hotel,HotelDto.class),rooms);
    }
}
