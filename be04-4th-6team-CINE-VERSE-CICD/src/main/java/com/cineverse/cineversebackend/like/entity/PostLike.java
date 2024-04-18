package com.cineverse.cineversebackend.like.entity;

import com.cineverse.cineversebackend.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_like")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostLike {
    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeId;

    @Column(name = "member_id")
    private int memberId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "review_id")
    private Integer reviewId;

    @Column(name = "free_id")
    private Integer freeId;

    @Column(name = "info_id")
    private Integer infoId;
}
