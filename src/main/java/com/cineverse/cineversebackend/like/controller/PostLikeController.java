package com.cineverse.cineversebackend.like.controller;

import com.cineverse.cineversebackend.like.service.PostLikeService;
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
    public ResponseEntity<?> likeReview(
            @PathVariable int reviewId,
            @PathVariable int memberId)
    {
        postLikeService.likeReview(reviewId, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/free/{freeId}/{memberId}")
    public ResponseEntity<?> likeFree(
            @PathVariable int freeId,
            @PathVariable int memberId)
    {
        postLikeService.likeFree(freeId, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/Info/{infoId}/{memberId}")
    public ResponseEntity<?> likeInfo(
            @PathVariable int infoId,
            @PathVariable int memberId)
    {
        postLikeService.likeInfo(infoId, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/event/{eventId}/{memberId}")
    public ResponseEntity<?> likeEvent(
            @PathVariable int eventId,
            @PathVariable int memberId)
    {
        postLikeService.likeEvent(eventId, memberId);
        return ResponseEntity.ok().build();
    }
}
