package com.cineverse.cineversebackend.user.member.service;

import com.cineverse.cineversebackend.user.member.dto.MemberDTO;
import com.cineverse.cineversebackend.user.member.entity.Member;

public interface MemberService {
    Member registMember(Member member);

    Member sessionLogin(String userId);

    Member deleteMember(int memberId);

    Member modifyMember(int memberId, MemberDTO memberDTO);

    Member findMemberById(int memberId);

    void changePwd(String newPwd, String to);

    Member findUserId(MemberDTO memberDTO);
}
