package com.cineverse.cineversebackend.review.comment.controller;

import com.cineverse.cineversebackend.info.comment.dto.InfoCommentDTO;
import com.cineverse.cineversebackend.info.comment.entity.InfoComment;
import com.cineverse.cineversebackend.review.comment.dto.ReviewCommentDTO;
import com.cineverse.cineversebackend.review.comment.entity.ReviewComment;
import com.cineverse.cineversebackend.review.comment.service.ReviewCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review_comment")
public class ReviewCommentController {

    private ReviewCommentService reviewCommentService;
    private ModelMapper modelMapper;

    @Autowired
    public ReviewCommentController(ReviewCommentService reviewCommentService, ModelMapper modelMapper) {
        this.reviewCommentService = reviewCommentService;
        this.modelMapper = modelMapper;
    }

    /* 설명. 댓글 작성 */
    @PostMapping("/regist")
    @Operation(summary = "리뷰 게시글 댓글 등록", description = "리뷰 게시글 댓글을 등록 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    private ResponseEntity<ReviewComment> registReviewComment(
            @Parameter(required = true, description = "댓글 등록 요청")
            @RequestBody ReviewCommentDTO reviewCommentDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ReviewComment reviewComment = modelMapper.map(reviewCommentDTO, ReviewComment.class);
        reviewCommentService.registReviewComment(reviewComment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 설명. 댓글 수정 */
    @PatchMapping("/modify/{reviewCommentId}")
    @Operation(summary = "리뷰 게시글 댓글 수정", description = "리뷰 게시글 댓글을 수정 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<ReviewComment> modifyReviewComment(
            @Parameter(required = true, description = "댓글 수정 요청")
            @RequestBody ReviewCommentDTO reviewCommentDTO,
            @Parameter(required = true, description = "댓글 고유번호")
            @PathVariable int reviewCommentId) {
        return ResponseEntity.ok(reviewCommentService.modifyReviewComment(reviewCommentId, reviewCommentDTO));
    }

    /* 설명. 댓글 삭제 */
    @PatchMapping("/delete/{reviewCommentId}")
    @Operation(summary = "리뷰 게시글 댓글 삭제", description = "리뷰 게시글 댓글을 삭제 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<ReviewComment> deleteReviewComment(
            @Parameter(required = true, description = "댓글 고유번호")
            @PathVariable int reviewCommentId) {
        return ResponseEntity
                .ok(reviewCommentService.modifyReviewCommentDeleteDate(reviewCommentId));
    }

    /* 설명. 댓글 전체 조회 */
    @GetMapping("/list")
    @Operation(summary = "리뷰 게시글 전체 댓글 조회", description = "리뷰 게시글 전체 댓글을 조회 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    public List<ReviewComment> findReviewCommentList() {
        List<ReviewComment> reviewCommentList = reviewCommentService.findReviewCommentList();

        return reviewCommentList;
    }
}
