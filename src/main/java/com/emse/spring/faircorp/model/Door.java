package com.emse.spring.faircorp.model;

import javax.persistence.*;

@Entity
public class Door {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DoorStatus status;

    @ManyToOne
    private Room room;

    public Door(Long id, String name, DoorStatus status, Room room) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.room = room;
    }

    public Door() {

    }

    public Door(String name, DoorStatus doorStatus, Room room) {
        this.name = name;
        this.status = doorStatus;
        this.room = room;
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

    public DoorStatus getStatus() {
        return status;
    }

    public void setStatus(DoorStatus status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
