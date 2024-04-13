package com.cineverse.cineversebackend.review.comment.service;

import com.cineverse.cineversebackend.review.comment.dto.ReviewCommentDTO;
import com.cineverse.cineversebackend.review.comment.entity.ReviewComment;

import java.util.List;

public interface ReviewCommentService {
    void registReviewComment(ReviewComment reviewComment);

    ReviewComment modifyReviewComment(int reviewCommentId, ReviewCommentDTO reviewCommentDTO);

    ReviewComment modifyReviewCommentDeleteDate(int reviewCommentId, ReviewCommentDTO reviewCommentDTO);

    List<ReviewComment> findReviewCommentList();
}
