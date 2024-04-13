package com.cineverse.cineversebackend.review.comment.repo;

import com.cineverse.cineversebackend.review.comment.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer> {
    List<ReviewComment> findByCommentDeleteDateIsNullOrderByReviewCommentIdDesc();
}
