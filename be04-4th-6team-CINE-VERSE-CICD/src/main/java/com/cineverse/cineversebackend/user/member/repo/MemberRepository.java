package com.cineverse.cineversebackend.user.member.repo;

import com.cineverse.cineversebackend.user.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByUserId(String userId);

    Member findByMemberEmail(String to);
}
