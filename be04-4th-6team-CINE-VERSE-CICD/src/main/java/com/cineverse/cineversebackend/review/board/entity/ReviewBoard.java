package com.cineverse.cineversebackend.review.board.entity;

import com.cineverse.cineversebackend.image.entity.Image;
import com.cineverse.cineversebackend.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_review_board")
@Builder
public class ReviewBoard {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    @Column(name = "review_title")
    private String reviewTitle;

    @Column(name = "review_content")
    private String reviewContent;

    @Column(name = "review_view_count")
    private int reviewViewCount;

    @Column(name = "review_date")
    private String reviewDate;

    @Column(name = "review_delete_date")
    private String reviewDeleteDate;

    @Column(name = "review_star")
    private int reviewStar;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @JoinColumn(name = "review_category_id")
    @ManyToOne
    private ReviewCategory reviewCategory;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

}
