package com.cineverse.cineversebackend.point.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_member_point")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_point_id")
    int memberPointId;

    @Column(name = "member_id")
    int memberId;

    @Column(name = "member_point")
    int memberPoint;

    @Column(name = "point_log")
    String pointLog;
}
