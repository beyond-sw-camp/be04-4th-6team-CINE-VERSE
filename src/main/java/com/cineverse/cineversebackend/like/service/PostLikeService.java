package com.cineverse.cineversebackend.like.service;

public interface PostLikeService {
    void likeReview(int reviewId, int memberId);

    void likeFree(int freeId, int memberId);

    void likeInfo(int infoId, int memberId);

    void likeEvent(int eventId, int memberId);
}
