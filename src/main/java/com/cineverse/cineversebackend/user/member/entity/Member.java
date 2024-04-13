package com.cineverse.cineversebackend.user.member.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "tbl_member")
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_number")
    private String memberNumber;

    @Column(name = "join_date")
    private String joinDate;

    @Column(name = "withdrawal_date")
    private String withdrawalDate;

    @Column(name = "member_status")
    private String memberStatus;

    @Column(name = "report_num")
    private Integer reportNum;

    @Column(name = "grade_id")
    private int gradeId;

    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "member_date")
    private String memberDate;
}
