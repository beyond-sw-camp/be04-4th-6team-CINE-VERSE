package com.cineverse.cineversebackend.badge.dto;

import com.cineverse.cineversebackend.badge.entity.Badge;
import com.cineverse.cineversebackend.user.member.entity.Member;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberBadgeDTO {
    private int memberBadgeId;
    private Badge badge;
    private Member member;
    private String badgeStatus;
}
