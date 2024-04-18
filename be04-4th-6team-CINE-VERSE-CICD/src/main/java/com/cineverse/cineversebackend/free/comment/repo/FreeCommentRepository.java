package com.cineverse.cineversebackend.free.comment.repo;

import com.cineverse.cineversebackend.free.comment.entity.FreeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreeCommentRepository extends JpaRepository<FreeComment, Integer> {
    List<FreeComment> findByCommentDeleteDateIsNullOrderByFreeCommentIdDesc();
}
