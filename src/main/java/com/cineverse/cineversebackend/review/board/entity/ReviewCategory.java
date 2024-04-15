package com.cineverse.cineversebackend.review.board.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_review_category")
@Builder
public class ReviewCategory {
    @Id
    @Column(name = "review_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewCategoryId;

    @Column(name = "review_category")
    private String reviewCategory;
}
