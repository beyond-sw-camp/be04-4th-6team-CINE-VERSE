package com.cineverse.cineversebackend.point.service;

import com.cineverse.cineversebackend.point.dto.PointDTO;

import java.util.List;

public interface PointService {
    void addBoardPoint(int memberId);

    void usePoints(int memberId, int memberPoint);

    List<PointDTO> getMemberPoints(int memberId);
}
