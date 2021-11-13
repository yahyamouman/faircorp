package com.emse.spring.faircorp.controller;

import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.DoorDao;
import com.emse.spring.faircorp.dto.DoorDto;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Door;
import com.emse.spring.faircorp.model.DoorStatus;
import com.emse.spring.faircorp.service.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DoorController {

    @Autowired
    private DoorDao doorDao;
    @Autowired
    private DoorService doorService;
    @Autowired
    private RoomDao roomDao;

    @GetMapping
    public List<DoorDto> findAll() {
        return doorDao.findAll().stream().map(DoorDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/open")
    public List<DoorDto> findAllOpen() {
        return doorService.getAllOpenDoors().stream().map(DoorDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public DoorDto findById(@PathVariable Long id) {
        return doorDao.findById(id).map(DoorDto::new).orElse(null); // (7)
    }

    @PutMapping(path = "/{id}/switch")
    public DoorDto switchStatus(@PathVariable Long id) {
        Door door = doorDao.findById(id).orElseThrow(IllegalArgumentException::new);
        door.setStatus(door.getStatus() == DoorStatus.OPEN ? DoorStatus.CLOSED: DoorStatus.OPEN);
        return new DoorDto(door);
    }

    @PutMapping(path = "/{id}/lock")
    public DoorDto lockDoor(@PathVariable Long id) {
        Door door = doorDao.findById(id).orElseThrow(IllegalArgumentException::new);
        door.setStatus(door.getStatus() != DoorStatus.OPEN ? DoorStatus.LOCKED: DoorStatus.OPEN);
        return new DoorDto(door);
    }

    @PostMapping(path = "/")
    public DoorDto create(@RequestBody DoorDto dto) {
        Room room = roomDao.getById(dto.getRoomId());
        Door door;
        if (dto.getId() == null) {
            door = doorDao.save(new Door(dto.getName(), dto.getDoorStatus(), room));
        }
        else {
            door = doorDao.getById(dto.getId());
            door.setStatus(dto.getDoorStatus());
        }
        return new DoorDto(door);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        doorDao.deleteById(id);
    }
}
