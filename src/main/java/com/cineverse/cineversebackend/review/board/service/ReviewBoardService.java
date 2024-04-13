package com.cineverse.cineversebackend.review.board.service;

import com.cineverse.cineversebackend.review.board.dto.ReviewBoardDTO;
import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewBoardService {

    ReviewBoardDTO findReviewById(int boardId);

    List<ReviewBoard> findReivewList();

    ReviewBoard deleteReview(int boardId);

    void registReview(ReviewBoardDTO newReview, MultipartFile[] images);

    void modifyReview(int boardId, ReviewBoardDTO review, MultipartFile[] images);

}
