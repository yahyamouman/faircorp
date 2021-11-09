package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Door;
import com.emse.spring.faircorp.model.DoorStatus;
import com.emse.spring.faircorp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface DoorDao extends JpaRepository<Door,Long> {

    @Modifying
    @Query("delete from Door d where d.room.id =:roomId")
    void deleteDoorsByRoomId(@Param("roomId") Long id);

    Collection<Door> findAllByStatus(DoorStatus status);
    Collection<Door> findAllByRoom(Room room);
    Optional<Door> findDoorByName(String name);

}
