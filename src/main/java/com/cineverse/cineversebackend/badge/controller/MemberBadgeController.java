package com.cineverse.cineversebackend.badge.controller;

import com.cineverse.cineversebackend.badge.dto.MemberBadgeDTO;
import com.cineverse.cineversebackend.badge.entity.Badge;
import com.cineverse.cineversebackend.badge.entity.MemberBadge;
import com.cineverse.cineversebackend.badge.service.MemberBadgeService;
import com.cineverse.cineversebackend.point.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/badge")
public class MemberBadgeController {
    private final MemberBadgeService memberBadgeService;
    private final PointService pointService;

    @Autowired
    public MemberBadgeController(MemberBadgeService memberBadgeService, PointService pointService) {
        this.memberBadgeService = memberBadgeService;
        this.pointService = pointService;
    }

    /* 설명. 뱃지 구매 */
    @PostMapping("/buy")
    public ResponseEntity<MemberBadge> buyBadge(@RequestBody MemberBadgeDTO memberBadge) {
        return ResponseEntity.ok(memberBadgeService.buyBadge(memberBadge));
    }


    /* 설명. 뱃지 사용 */
    @PostMapping("/use")
    public ResponseEntity<MemberBadge> useBadge(@RequestBody MemberBadgeDTO memberBadge) {
        System.out.println(memberBadge.getMember().getMemberId());
        return ResponseEntity.ok(memberBadgeService.useBadge(memberBadge));
    }

    /* 설명. 보유한 뱃지 조회 */
    @GetMapping("/{memberId}")
    public List<MemberBadge> membersBadge(@PathVariable int memberId) {
        return memberBadgeService.membersBadge(memberId);
    }

    /* 설명. 뱃지 전체 조회 */
    @GetMapping("/list")
    public List<Badge> findAllBadge() {
        return memberBadgeService.findAllBadge();
    }

}
