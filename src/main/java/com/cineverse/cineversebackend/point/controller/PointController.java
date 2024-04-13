package com.cineverse.cineversebackend.point.controller;

import com.cineverse.cineversebackend.point.dto.PointDTO;
import com.cineverse.cineversebackend.point.service.PointService;
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
    public ResponseEntity<?> usePoints(@PathVariable int memberId, @RequestBody PointDTO pointDTO) {
        try {
            pointService.usePoints(memberId, pointDTO.getMemberPoint());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류 발생: " + e.getMessage());
        }
    }

    /* 설명. 회원별 포인트 조회 */
    @GetMapping("/{memberId}")
    public ResponseEntity<List<PointDTO>> getMemberPoints(@PathVariable int memberId) {
        try {
            List<PointDTO> pointDTOS = pointService.getMemberPoints(memberId);
            return ResponseEntity.ok(pointDTOS);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
