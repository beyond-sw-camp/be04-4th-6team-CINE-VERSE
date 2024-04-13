package com.cineverse.cineversebackend.info.comment.repo;

import com.cineverse.cineversebackend.info.comment.entity.InfoComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoCommentRepository extends JpaRepository<InfoComment, Integer> {
    List<InfoComment> findByCommentDeleteDateIsNullOrderByInfoCommentIdDesc();
}
