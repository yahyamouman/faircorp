package com.emse.spring.faircorp.service;

import com.emse.spring.faircorp.dao.DoorDao;
import com.emse.spring.faircorp.model.Door;
import com.emse.spring.faircorp.model.DoorStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DoorService {

    @Autowired
    DoorDao doorDao;

    public Boolean lockDoor(long id){
        Door door = doorDao.getById(id);
        if (door.getStatus().equals(DoorStatus.CLOSED)){
            door.setStatus(DoorStatus.LOCKED);
            return true;
        }
        return false;
    }

    public Boolean lockDoor(String name){
        Door door = doorDao.findDoorByName(name).get();
        if (door.getStatus().equals(DoorStatus.CLOSED)){
            door.setStatus(DoorStatus.LOCKED);
            return true;
        }
        return false;
    }

    public Boolean unlockDoor(long id){
        Door door = doorDao.getById(id);
        if (door.getStatus().equals(DoorStatus.LOCKED)){
            door.setStatus(DoorStatus.CLOSED);
            return true;
        }
        return false;
    }

    public Boolean unlockDoor(String name){
        Door door = doorDao.findDoorByName(name).get();
        if (door.getStatus().equals(DoorStatus.LOCKED)){
            door.setStatus(DoorStatus.CLOSED);
            return true;
        }
        return false;
    }

    public void lockAllDoors(){
        ArrayList<Door> doors = (ArrayList<Door>) doorDao.findAllByStatus(DoorStatus.CLOSED);
        for (Door door:doors) {
            door.setStatus(DoorStatus.LOCKED);
        }
    }

    public ArrayList<Door> getAllOpenDoors(){
        return (ArrayList<Door>) doorDao.findAllByStatus(DoorStatus.OPEN);
    }

    public ArrayList<Door> getAllLockedDoors(){
        return (ArrayList<Door>) doorDao.findAllByStatus(DoorStatus.LOCKED);
    }

}
