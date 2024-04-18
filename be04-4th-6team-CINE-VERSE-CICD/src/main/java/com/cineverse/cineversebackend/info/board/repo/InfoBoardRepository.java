package com.cineverse.cineversebackend.info.board.repo;

import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoBoardRepository extends JpaRepository<InfoBoard, Integer> {
    List<InfoBoard> findByInfoDeleteDateIsNullOrderByInfoIdDesc();
}
