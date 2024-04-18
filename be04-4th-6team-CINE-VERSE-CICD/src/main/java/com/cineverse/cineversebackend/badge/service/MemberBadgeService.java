package com.cineverse.cineversebackend.badge.service;

import com.cineverse.cineversebackend.badge.dto.MemberBadgeDTO;
import com.cineverse.cineversebackend.badge.entity.Badge;
import com.cineverse.cineversebackend.badge.entity.MemberBadge;

import java.util.List;

public interface MemberBadgeService {
    MemberBadge buyBadge(MemberBadgeDTO memberBadgeDTO);

    MemberBadge useBadge(MemberBadgeDTO memberBadge);

    List<MemberBadge> membersBadge(int memberId);

    List<Badge> findAllBadge();
}
