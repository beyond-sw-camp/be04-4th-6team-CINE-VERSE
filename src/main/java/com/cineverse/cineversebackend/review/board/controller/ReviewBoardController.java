package com.cineverse.cineversebackend.review.board.controller;

import com.cineverse.cineversebackend.review.board.dto.ReviewBoardDTO;
import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
import com.cineverse.cineversebackend.review.board.service.ReviewBoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;

/* 젠킨스 주석 */
@RestController
@RequestMapping("/review_board")
public class ReviewBoardController {
    private final ReviewBoardService reviewBoardService;

    @Autowired
    public ReviewBoardController(ReviewBoardService reviewBoardService) {
        this.reviewBoardService = reviewBoardService;
    }

    /* 설명. 리뷰 생성 */
    @PostMapping("/regist")
    public ResponseEntity<ReviewBoardDTO> registReview(
            @RequestPart("reviewBoard") String reviewBoardJson,
            @RequestPart(value = "images", required = false) MultipartFile[] images) throws JsonProcessingException {
        String utf8Json = new String(reviewBoardJson.
                getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        ReviewBoardDTO newReview = objectMapper.readValue(utf8Json, ReviewBoardDTO.class);

        reviewBoardService.registReview(newReview, images);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
    }

    /* 설명. 리뷰 단일 조회 */
    @GetMapping("/{boardId}")
    public ReviewBoardDTO findReviewById(@PathVariable int boardId) {
        ReviewBoardDTO review = reviewBoardService.findReviewById(boardId);

        return review;
    }

    /* 설명. 리뷰 다중 조회 */
    @GetMapping("/list")
    public List<ReviewBoard> findReviewList() {
        List<ReviewBoard> reviewList = reviewBoardService.findReivewList();

        return reviewList;
    }

    /* 설명. 게시글 수정 */
    @PatchMapping("/modify/{boardId}")
    public ResponseEntity<ReviewBoard> modifyReview(
            @RequestPart("review") String reviewJson,
            @RequestPart(value = "images", required = false) MultipartFile[] images,
            @PathVariable int boardId) throws JsonProcessingException {
        String utf8Json = new String(reviewJson.
                getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        ReviewBoardDTO review = objectMapper.readValue(utf8Json, ReviewBoardDTO.class);
        reviewBoardService.modifyReview(boardId, review, images);
        return ResponseEntity.ok().build();
    }

    /* 설명. 리뷰 삭제 */
    @PatchMapping("/delete/{boardId}")
    public ResponseEntity<ReviewBoard> deleteReview(@PathVariable int boardId) {
        return ResponseEntity.ok(reviewBoardService.deleteReview(boardId));
    }
}
