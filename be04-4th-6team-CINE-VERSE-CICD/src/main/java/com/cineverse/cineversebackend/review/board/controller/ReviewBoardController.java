package com.cineverse.cineversebackend.review.board.controller;

import com.cineverse.cineversebackend.review.board.dto.ReviewBoardDTO;
import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
import com.cineverse.cineversebackend.review.board.service.ReviewBoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;

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
    @Operation(summary = "리뷰 게시글 등록", description = "리뷰 게시글을 등록 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<ReviewBoardDTO> registReview(
            @Parameter(required = true, description = "게시글 등록 요청")
            @RequestPart("reviewBoard") String reviewBoardJson,
            @Parameter(required = false, description = "게시글 이미지")
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
    @Operation(summary = "단일 리뷰 게시글 조회", description = "단일 리뷰 게시글을 조회 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ReviewBoardDTO findReviewById(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int boardId) {
        ReviewBoardDTO review = reviewBoardService.findReviewById(boardId);

        return review;
    }

    /* 설명. 리뷰 다중 조회 */
    @GetMapping("/list")
    @Operation(summary = "전체 리뷰 게시글 조회", description = "전체 리뷰 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    public List<ReviewBoard> findReviewList() {
        List<ReviewBoard> reviewList = reviewBoardService.findReivewList();

        return reviewList;
    }

    /* 설명. 게시글 수정 */
    @PatchMapping("/modify/{boardId}")
    @Operation(summary = "리뷰 게시글 수정", description = "전체 리뷰 게시글을 수정 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<ReviewBoard> modifyReview(
            @Parameter(required = true, description = "게시글 수정 요청")
            @RequestPart("review") String reviewJson,
            @Parameter(required = false, description = "게시글 이미지")
            @RequestPart(value = "images", required = false) MultipartFile[] images,
            @Parameter(required = true, description = "게시글 고유 번호")
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
    @Operation(summary = "리뷰 게시글 삭제", description = "리뷰 게시글을 삭제 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<ReviewBoard> deleteReview(
            @Parameter(required = true, description = "게시글 고유번호")
            @PathVariable int boardId) {
        return ResponseEntity.ok(reviewBoardService.deleteReview(boardId));
    }
}
