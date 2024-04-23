package com.cineverse.cineversebackend.like.repo;

import com.cineverse.cineversebackend.like.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
    Optional<PostLike> findByReviewIdAndMemberId(int reviewId, int memberId);

    Optional<PostLike> findByFreeIdAndMemberId(int freeId, int memberId);

    Optional<PostLike> findByInfoIdAndMemberId(int infoId, int memberId);

    Optional<PostLike> findByEventIdAndMemberId(int eventId, int memberId);

    PostLike findByMemberId(int memberId);
}
