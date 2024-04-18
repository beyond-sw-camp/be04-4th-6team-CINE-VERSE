package com.cineverse.cineversebackend.review.comment.entity;

import com.cineverse.cineversebackend.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_review_comment")
@Builder
public class ReviewComment {

    @Id
    @Column(name = "review_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewCommentId;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_date")
    private String commentDate;

    @Column(name = "comment_delete_date")
    private String commentDeleteDate;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @Column(name = "review_id")
    private int reviewId;
}
