package com.cineverse.cineversebackend.user.member.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberDTO {
    private int memberId;
    private String userId;
    private String userPassword;
    private String nickname;
    private String memberName;
    private String memberNumber;
    private String joinDate;
    private String withdrawalDate;
    private String memberStatus;
    private Integer reportNum;
    private int gradeId;
    private String memberEmail;
    private String memberDate;
}
