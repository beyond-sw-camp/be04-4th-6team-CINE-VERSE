package com.cineverse.cineversebackend.like.controller;

import com.cineverse.cineversebackend.like.service.PostLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class PostLikeController {
    private final PostLikeService postLikeService;

    @Autowired
    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    /* 설명. 리뷰 좋아요 */
    @GetMapping("/review/{reviewId}/{memberId}")
    @Operation(summary = "리뷰 게시글 좋아요 등록", description = "리뷰 게시글 좋아요를 등록 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<?> likeReview(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int reviewId,
            @Parameter(required = true, description = "회원 고유 번호")
            @PathVariable int memberId)
    {
        postLikeService.likeReview(reviewId, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/free/{freeId}/{memberId}")
    @Operation(summary = "자유 게시글 좋아요 등록", description = "자유 게시글 좋아요를 등록 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<?> likeFree(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int freeId,
            @Parameter(required = true, description = "회원 고유 번호")
            @PathVariable int memberId)
    {
        postLikeService.likeFree(freeId, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/Info/{infoId}/{memberId}")
    @Operation(summary = "정보 게시글 좋아요 등록", description = "정보 게시글 좋아요를 등록 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<?> likeInfo(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int infoId,
            @Parameter(required = true, description = "회원 고유 번호")
            @PathVariable int memberId)
    {
        postLikeService.likeInfo(infoId, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/event/{eventId}/{memberId}")
    @Operation(summary = "이벤트 게시글 좋아요 등록", description = "이벤트 게시글 좋아요를 등록 합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<?> likeEvent(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int eventId,
            @Parameter(required = true, description = "회원 고유 번호")
            @PathVariable int memberId)
    {
        postLikeService.likeEvent(eventId, memberId);
        return ResponseEntity.ok().build();
    }
}
