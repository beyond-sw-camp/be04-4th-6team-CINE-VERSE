package com.cineverse.cineversebackend.event.board.repo;

import com.cineverse.cineversebackend.event.board.entity.EventBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventBoardRepository extends JpaRepository<EventBoard, Integer> {
    List<EventBoard> findByEventDeleteDateIsNullOrderByEventBoardIdDesc();
}
