package com.cineverse.cineversebackend.like.service;

import com.cineverse.cineversebackend.like.dto.PostLikeDTO;

public interface PostLikeService {
    void likeReview(int reviewId, int memberId);

    void likeFree(int freeId, int memberId);

    void likeInfo(int infoId, int memberId);

    void likeEvent(int eventId, int memberId);

    PostLikeDTO findLikeList(int memberId);
}
