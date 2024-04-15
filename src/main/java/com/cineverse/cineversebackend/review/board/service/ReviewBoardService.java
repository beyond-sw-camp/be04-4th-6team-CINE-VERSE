package com.cineverse.cineversebackend.review.board.service;

import com.cineverse.cineversebackend.review.board.dto.ReviewBoardDTO;
import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewBoardService {

    ReviewBoardDTO findReviewById(int boardId);

    List<ReviewBoard> findReivewList();

    ReviewBoard deleteReview(int boardId);

    ReviewBoard registReview(ReviewBoardDTO newReview, MultipartFile[] images);

    ReviewBoard modifyReview(int boardId, ReviewBoardDTO review, MultipartFile[] images);

}
