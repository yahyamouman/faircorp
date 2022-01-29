package com.emse.spring.faircorp.controller;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.dto.WindowDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/faircorp/room")
@Transactional
public class RoomController {
    @Autowired
    private RoomDao roomDao;
    @Autowired
    private BuildingDao buildingDao;

    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{room_id}")
    public RoomDto findById(@PathVariable Long room_id) {
        return roomDao.findById(room_id).map(RoomDto::new).orElse(null);
    }

    @DeleteMapping(path = "/{room_id}")
    public void delete(@PathVariable Long room_id) {
        roomDao.deleteById(room_id);
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto dto) {
        Building building = buildingDao.getById(dto.getBuildingId());
        Room room = new Room(dto.getName(), dto.getFloor(), dto.getCurrentTemperature(), dto.getTargetTemperature(), building);
        room = roomDao.saveAndFlush(room);
        building = buildingDao.saveAndFlush(building);
        return new RoomDto(room);
    }
}
