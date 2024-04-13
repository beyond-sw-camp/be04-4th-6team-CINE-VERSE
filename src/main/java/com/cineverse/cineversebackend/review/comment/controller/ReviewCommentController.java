package com.cineverse.cineversebackend.review.comment.controller;

import com.cineverse.cineversebackend.info.comment.dto.InfoCommentDTO;
import com.cineverse.cineversebackend.info.comment.entity.InfoComment;
import com.cineverse.cineversebackend.review.comment.dto.ReviewCommentDTO;
import com.cineverse.cineversebackend.review.comment.entity.ReviewComment;
import com.cineverse.cineversebackend.review.comment.service.ReviewCommentService;
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
    private ResponseEntity<ReviewComment> registReviewComment(@RequestBody ReviewCommentDTO reviewCommentDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ReviewComment reviewComment = modelMapper.map(reviewCommentDTO, ReviewComment.class);
        reviewCommentService.registReviewComment(reviewComment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 설명. 댓글 수정 */
    @PatchMapping("/modify/{reviewCommentId}")
    public ResponseEntity<ReviewComment> modifyReviewComment(@RequestBody ReviewCommentDTO reviewCommentDTO,
                                                             @PathVariable int reviewCommentId) {
        return ResponseEntity.ok(reviewCommentService.modifyReviewComment(reviewCommentId, reviewCommentDTO));
    }

    /* 설명. 댓글 삭제 */
    @PatchMapping("/delete/{reviewCommentId}")
    public ResponseEntity<ReviewComment> deleteReviewComment(@RequestBody ReviewCommentDTO reviewCommentDTO,
                                                             @PathVariable int reviewCommentId) {
        return ResponseEntity
                .ok(reviewCommentService.modifyReviewCommentDeleteDate(reviewCommentId, reviewCommentDTO));
    }

    /* 설명. 댓글 전체 조회 */
    @GetMapping("/list")
    public List<ReviewComment> findReviewCommentList() {
        List<ReviewComment> reviewCommentList = reviewCommentService.findReviewCommentList();

        return reviewCommentList;
    }
}
