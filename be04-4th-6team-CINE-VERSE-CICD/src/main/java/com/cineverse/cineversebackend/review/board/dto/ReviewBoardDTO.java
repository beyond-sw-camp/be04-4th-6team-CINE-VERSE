package com.cineverse.cineversebackend.review.board.dto;

import com.cineverse.cineversebackend.image.entity.Image;
import com.cineverse.cineversebackend.review.board.entity.ReviewCategory;
import com.cineverse.cineversebackend.user.member.entity.Member;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReviewBoardDTO {
    private int reviewId;
    private String reviewTitle;
    private String reviewContent;
    private int reviewViewCount;
    private String reviewDate;
    private String reviewDeleteDate;
    private Integer reviewStar;
    private Member member;
    private ReviewCategory reviewCategory;
    private List<Image> images;
}
