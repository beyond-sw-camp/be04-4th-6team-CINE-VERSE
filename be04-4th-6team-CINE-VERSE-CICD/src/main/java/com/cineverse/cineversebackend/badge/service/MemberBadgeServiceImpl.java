package com.cineverse.cineversebackend.badge.service;

import com.cineverse.cineversebackend.badge.dto.MemberBadgeDTO;
import com.cineverse.cineversebackend.badge.entity.Badge;
import com.cineverse.cineversebackend.badge.entity.MemberBadge;
import com.cineverse.cineversebackend.badge.repo.BadgeRepository;
import com.cineverse.cineversebackend.badge.repo.MemberBadgeRepository;
import com.cineverse.cineversebackend.point.service.PointService;
import com.cineverse.cineversebackend.user.member.entity.Member;
import com.cineverse.cineversebackend.user.member.repo.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberBadgeServiceImpl implements MemberBadgeService {

    private final MemberBadgeRepository memberBadgeRepository;
    private final BadgeRepository badgeRepository;
    private final MemberRepository memberRepository;
    private final PointService pointService;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberBadgeServiceImpl(MemberBadgeRepository memberBadgeRepository,
                                  PointService pointService,
                                  ModelMapper modelMapper,
                                  BadgeRepository badgeRepository,
                                  MemberRepository memberRepository
                                  )
    {
        this.memberBadgeRepository = memberBadgeRepository;
        this.pointService = pointService;
        this.modelMapper = modelMapper;
        this.badgeRepository = badgeRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public MemberBadge buyBadge(MemberBadgeDTO memberBadge) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Badge badge = badgeRepository.findById(memberBadge.getBadge().getBadgeId())
                .orElseThrow(() -> new EntityNotFoundException("뱃지를 찾을 수 없습니다."));
        Member member = memberRepository.findById(memberBadge.getMember().getMemberId())
                .orElseThrow(()-> new EntityNotFoundException("회원을 찾을 수 없습니다,"));

        MemberBadge boughtMemberBadge = new MemberBadge();
        boughtMemberBadge.setBadge(badge);
        boughtMemberBadge.setMember(member);
        boughtMemberBadge.setBadgeStatus("N");

        memberBadgeRepository.save(boughtMemberBadge);

        pointService.usePoints(memberBadge.getMember().getMemberId(), 1000);

        return boughtMemberBadge;
    }

    @Override
    @Transactional
    public MemberBadge useBadge(MemberBadgeDTO memberBadge) {
        Optional<MemberBadge> findMemberBadge = memberBadgeRepository.findByMember_MemberIdAndBadge_BadgeId(
                memberBadge.getMember().getMemberId(),
                memberBadge.getBadge().getBadgeId());

        List<MemberBadge> badges = memberBadgeRepository.findByMember_MemberId(memberBadge.getMember().getMemberId());

        if (findMemberBadge.isPresent()) {
            for (MemberBadge badge : badges) {
                badge.setBadgeStatus("N");
                memberBadgeRepository.save(badge);
            }

            MemberBadge currentBadge = findMemberBadge.get();
            currentBadge.setBadgeStatus("Y");
            return memberBadgeRepository.save(currentBadge);

        } else {
            System.out.println("존재하지 않는 뱃지입니다.");
            return null;
        }
    }

    @Override
    public List<MemberBadge> membersBadge(int memberId) {
        List<MemberBadge> badges = memberBadgeRepository.findByMember_MemberId(memberId);

        if (!badges.isEmpty()) {
            return badges;
        } else {
            return null;
        }
    }

    @Override
    public List<Badge> findAllBadge() {
        List<Badge> badges = badgeRepository.findAll(Sort.by("badgeId").descending());

        if (!badges.isEmpty()) {
            return badges;
        } else {
            return null;
        }
    }
}
