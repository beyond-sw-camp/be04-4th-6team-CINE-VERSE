package com.cineverse.cineversebackend.point.repo;

import com.cineverse.cineversebackend.point.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Integer> {
    List<Point> findByMemberId(int memberId);

    List<Point> findByMemberIdOrderByMemberPointIdDesc(int memberId);
}
