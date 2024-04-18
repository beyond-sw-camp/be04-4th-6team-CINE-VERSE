package com.cineverse.cineversebackend.free.board.repo;

import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Integer> {
    List<FreeBoard> findByFreeDeleteDateIsNullOrderByFreeIdDesc();
}
