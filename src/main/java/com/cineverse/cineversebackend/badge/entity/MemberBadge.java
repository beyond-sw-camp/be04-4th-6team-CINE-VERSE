package com.cineverse.cineversebackend.badge.entity;

import com.cineverse.cineversebackend.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_member_badge")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_badge_id")
    private int memberBadgeId;

    @JoinColumn(name = "badge_id")
    @ManyToOne
    private Badge badge;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @Column(name = "badge_status")
    private String badgeStatus;
}
