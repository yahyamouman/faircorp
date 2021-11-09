package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Door;
import com.emse.spring.faircorp.model.DoorStatus;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;

public class DoorDto {
    private Long id;
    private String name;
    private DoorStatus doorStatus;
    private String roomName;
    private Long roomId;

    public DoorDto() {
    }

    public DoorDto(Door door) {
        this.id = door.getId();
        this.name = door.getName();
        this.doorStatus = door.getStatus();
        this.roomName = door.getRoom().getName();
        this.roomId = door.getRoom().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(DoorStatus doorStatus) {
        this.doorStatus = doorStatus;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}