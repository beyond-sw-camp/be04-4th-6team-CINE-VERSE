package com.cineverse.cineversebackend.point.controller;

import com.cineverse.cineversebackend.point.dto.PointDTO;
import com.cineverse.cineversebackend.point.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    /* 설명. 포인트 사용 */
    @PostMapping("/use/{memberId}")
    @Operation(summary = "포인트 사용", description = "포인트를 사용합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<?> usePoints(
            @Parameter(required = true, description = "회원 고유번호")
            @PathVariable int memberId,
            @Parameter(required = true, description = "포인트 사용 요청")
            @RequestBody PointDTO pointDTO) {
        try {
            pointService.usePoints(memberId, pointDTO.getMemberPoint());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류 발생: " + e.getMessage());
        }
    }

    /* 설명. 회원별 포인트 조회 */
    @GetMapping("/{memberId}")
    @Operation(summary = "보유한 포인트 조회", description = "회원이 적립한 포인트 정보들을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<List<PointDTO>> getMemberPoints(
            @Parameter(required = true, description = "회원 고유번호")
            @PathVariable int memberId) {
        try {
            List<PointDTO> pointDTOS = pointService.getMemberPoints(memberId);
            return ResponseEntity.ok(pointDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
