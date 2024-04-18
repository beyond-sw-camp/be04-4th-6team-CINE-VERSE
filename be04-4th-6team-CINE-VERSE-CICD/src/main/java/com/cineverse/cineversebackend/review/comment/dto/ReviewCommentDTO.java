package com.cineverse.cineversebackend.review.comment.dto;

import com.cineverse.cineversebackend.user.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReviewCommentDTO {
    private int reviewCommentId;
    private String commentContent;
    private String commentDate;
    private String commentDeleteDate;
    private Member member;
    private int reviewId;
}
