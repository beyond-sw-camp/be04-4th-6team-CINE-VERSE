package com.cineverse.cineversebackend.point.service;

import com.cineverse.cineversebackend.like.entity.PostLike;
import com.cineverse.cineversebackend.point.dto.PointDTO;
import com.cineverse.cineversebackend.point.entity.Point;
import com.cineverse.cineversebackend.point.repo.PointRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;

    @Autowired
    public PointServiceImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    @Transactional
    public void addBoardPoint(int memberId) {
        Point newPoint = new Point();

        newPoint.setMemberPoint(100); // 포인트 값
        newPoint.setMemberId(memberId); // 멤버 ID 설정
        newPoint.setPointLog("게시글 작성"); // 포인트 로그 설정
        pointRepository.save(newPoint); // 새로운 포인트 로그 저장
    }

    @Override
    @Transactional
    public void usePoints(int memberId, int pointsToUse) {
        int totalPoints = calculateTotalPoints(memberId);

        if (totalPoints < pointsToUse) {
            throw new IllegalArgumentException("사용 가능한 포인트가 부족합니다.");
        }

        Point point = new Point();
        point.setMemberId(memberId);
        point.setMemberPoint(-pointsToUse);
        point.setPointLog("포인트 사용");
        pointRepository.save(point);
    }

    public int calculateTotalPoints(int memberId) {
        return pointRepository.findByMemberId(memberId).stream()
                .mapToInt(Point::getMemberPoint)
                .sum();
    }

    @Override
    public List<PointDTO> getMemberPoints(int memberId) {
        List<Point> points = pointRepository.findByMemberIdOrderByMemberPointIdDesc(memberId);
        int totalPoint = calculateTotalPoints(memberId);

        return points.stream()
                .map(point -> convertToDTO(point, totalPoint))
                .collect(Collectors.toList());
    }

    private PointDTO convertToDTO(Point point, int totalPoint) {
        // Point 엔티티를 PointDTO로 변환하는 로직 구현
        PointDTO dto = new PointDTO();
        dto.setMemberPointId(point.getMemberPointId());
        dto.setMemberId(point.getMemberId());
        dto.setMemberPoint(point.getMemberPoint());
        dto.setPointLog(point.getPointLog());
        dto.setTotalPoint(totalPoint);
        return dto;
    }

}
