package com.emse.spring.faircorp.controller;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/faircorp/heater")
@Transactional
public class HeaterController {

    @Autowired
    private HeaterDao heaterDao;
    @Autowired
    private RoomDao roomDao;

    @GetMapping
    public List<HeaterDto> findAll() {
        return heaterDao.findAll().stream().map(HeaterDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{heater_id}")
    public HeaterDto findById(@PathVariable Long heater_id) {
        return heaterDao.findById(heater_id).map(HeaterDto::new).orElse(null);
    }

    @DeleteMapping(path = "/{heater_id}")
    public void delete(@PathVariable Long heater_id) {
        heaterDao.deleteById(heater_id);
    }

    @PostMapping
    public HeaterDto create(@RequestBody HeaterDto dto) {
        Room room = roomDao.getById(dto.getRoomId());
        Heater heater = new Heater(dto.getName(), dto.getPower(), room, dto.getHeaterStatus());
        heater = heaterDao.saveAndFlush(heater);
        room = roomDao.saveAndFlush(room);
        return new HeaterDto(heater);
    }
}
