package com.cineverse.cineversebackend.review.board.repo;

import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewBoardRepository extends JpaRepository<ReviewBoard, Integer> {

    List<ReviewBoard> findByReviewDeleteDateIsNullOrderByReviewIdDesc();
}
