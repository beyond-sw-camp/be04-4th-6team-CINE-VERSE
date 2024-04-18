package com.cineverse.cineversebackend.badge.repo;

import com.cineverse.cineversebackend.badge.entity.MemberBadge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Integer> {
    Optional<MemberBadge> findByMember_MemberIdAndBadge_BadgeId(int memberId, int badgeId);

    List<MemberBadge> findByMember_MemberId(int memberBadgeId);
}
