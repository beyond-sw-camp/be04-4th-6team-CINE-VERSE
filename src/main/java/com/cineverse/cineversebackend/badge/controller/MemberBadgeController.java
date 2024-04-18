package com.cineverse.cineversebackend.badge.controller;

import com.cineverse.cineversebackend.badge.dto.MemberBadgeDTO;
import com.cineverse.cineversebackend.badge.entity.Badge;
import com.cineverse.cineversebackend.badge.entity.MemberBadge;
import com.cineverse.cineversebackend.badge.service.MemberBadgeService;
import com.cineverse.cineversebackend.point.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "뱃지 구매", description = "뱃지를 구매합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<MemberBadge> buyBadge(
            @Parameter(required = true, description = "뱃지 구매 요청")
            @RequestBody MemberBadgeDTO memberBadge) {
        return ResponseEntity.ok(memberBadgeService.buyBadge(memberBadge));
    }


    /* 설명. 뱃지 사용 */
    @PostMapping("/use")
    @Operation(summary = "뱃지 착용", description = "보유한 뱃지를 착용합니다")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<MemberBadge> useBadge(
            @Parameter(required = true, description = "뱃지 착용 요청")
            @RequestBody MemberBadgeDTO memberBadge) {
        System.out.println(memberBadge.getMember().getMemberId());
        return ResponseEntity.ok(memberBadgeService.useBadge(memberBadge));
    }

    /* 설명. 보유한 뱃지 조회 */
    @GetMapping("/{memberId}")
    @Operation(summary = "보유 뱃지 조회", description = "회원이 보유한 뱃지 전체를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public List<MemberBadge> membersBadge(
            @Parameter(required = true, description = "멤버 고유 번호")
            @PathVariable int memberId) {
        return memberBadgeService.membersBadge(memberId);
    }

    /* 설명. 뱃지 전체 조회 */
    @GetMapping("/list")
    @Operation(summary = "전체 뱃지 조회", description = "서비스에서 보유한 뱃지를 전체 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    public List<Badge> findAllBadge() {
        return memberBadgeService.findAllBadge();
    }

}
