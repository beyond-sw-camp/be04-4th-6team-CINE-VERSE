package com.cineverse.cineversebackend.image.repo;

import com.cineverse.cineversebackend.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByReview_ReviewId(int reviewId);
    List<Image> findByInfo_InfoId(int reviewId);

    List<Image> findByFree_FreeId(int freeId);
}
