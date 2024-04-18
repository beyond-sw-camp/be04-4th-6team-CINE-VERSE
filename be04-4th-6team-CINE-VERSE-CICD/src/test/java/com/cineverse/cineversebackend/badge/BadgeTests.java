package com.cineverse.cineversebackend.badge;

import com.cineverse.cineversebackend.badge.dto.MemberBadgeDTO;
import com.cineverse.cineversebackend.badge.entity.Badge;
import com.cineverse.cineversebackend.badge.entity.MemberBadge;
import com.cineverse.cineversebackend.badge.repo.BadgeRepository;
import com.cineverse.cineversebackend.badge.repo.MemberBadgeRepository;
import com.cineverse.cineversebackend.badge.service.MemberBadgeService;
import com.cineverse.cineversebackend.user.member.entity.Member;
import com.cineverse.cineversebackend.user.member.repo.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BadgeTests {

    private final BadgeRepository badgeRepository;
    private final MemberBadgeRepository memberBadgeRepository;
    private final MemberBadgeService memberBadgeService;
    private final MemberRepository memberRepository;

    @Autowired
    public BadgeTests(BadgeRepository badgeRepository,
                      MemberBadgeService memberBadgeService,
                      MemberRepository memberRepository,
                      MemberBadgeRepository memberBadgeRepository) {
        this.badgeRepository = badgeRepository;
        this.memberBadgeService = memberBadgeService;
        this.memberRepository = memberRepository;
        this.memberBadgeRepository = memberBadgeRepository;
    }

    @Test
    @Transactional
    @DisplayName("뱃지 구매 성공 테스트")
    public void successBuyBadgeTest() {
        Optional<Member> optionalMember = memberRepository.findById(5);
        Member member = optionalMember.get();

        Optional<Badge> optionalBadge = badgeRepository.findById(1);
        Badge badge = optionalBadge.get();

        MemberBadgeDTO memberBadge = MemberBadgeDTO.builder()
                .member(member)
                .badge(badge)
                .build();

        MemberBadge boughtBadge = memberBadgeService.buyBadge(memberBadge);

        assertThat(memberBadge.getBadge()).isEqualTo(boughtBadge.getBadge());
        assertThat(memberBadge.getMember()).isEqualTo(boughtBadge.getMember());
        assertThat(boughtBadge.getBadgeStatus()).isEqualTo("N");
    }

    @Test
    @Transactional
    @DisplayName("회원이 보유한 뱃지 전체 조회 성공 테스트")
    public void findMembersBadgesTest() {
        List<MemberBadge> testData = memberBadgeRepository.findByMember_MemberId(1);

        List<MemberBadge> memberBadges = memberBadgeService.membersBadge(1);

        assertThat(testData).isEqualTo(memberBadges);
    }

    @Test
    @Transactional
    @DisplayName("뱃지 전체 조회 성공 테스트")
    public void findAllBadgesTest() {
        List<Badge> badges = memberBadgeService.findAllBadge();

        assertThat(badges).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("뱃지 착용 성공 테스트")
    public void successUseBadgeTest() {
        Optional<MemberBadge> optionalMemberBadge = memberBadgeRepository.findByMember_MemberIdAndBadge_BadgeId(1, 1);
        MemberBadge testData = optionalMemberBadge.get();

        assertThat(testData.getBadgeStatus()).isEqualTo("N");

        Optional<Member> optionalMember = memberRepository.findById(1);
        Member member = optionalMember.get();

        Optional<Badge> optionalBadge = badgeRepository.findById(1);
        Badge badge = optionalBadge.get();

        MemberBadgeDTO memberBadgeDTO = MemberBadgeDTO.builder()
                .member(member)
                .badge(badge)
                .build();

        MemberBadge memberBadge = memberBadgeService.useBadge(memberBadgeDTO);


        assertThat(memberBadge.getBadgeStatus()).isEqualTo("Y");
    }
}
