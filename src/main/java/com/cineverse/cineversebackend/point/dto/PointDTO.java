package com.cineverse.cineversebackend.point.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PointDTO {
    int memberPointId;
    int memberId;
    int memberPoint;
    int totalPoint;
    String pointLog;
}
