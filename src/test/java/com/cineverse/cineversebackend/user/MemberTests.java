package com.cineverse.cineversebackend.user;

import com.cineverse.cineversebackend.user.email.dto.EmailMessage;
import com.cineverse.cineversebackend.user.email.service.EmailService;
import com.cineverse.cineversebackend.user.member.dto.MemberDTO;
import com.cineverse.cineversebackend.user.member.entity.Member;
import com.cineverse.cineversebackend.user.member.repo.MemberRepository;
import com.cineverse.cineversebackend.user.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MemberTests {

    private final MemberService memberService;
    private final EmailService emailService;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberTests(MemberService memberService,
                       EmailService emailService,
                       MemberRepository memberRepository) {
        this.memberService = memberService;
        this.emailService = emailService;
        this.memberRepository = memberRepository;
    }

    @BeforeEach
    public void setup() {
        Member testMember = Member.builder()
                .userId("testUser")
                .userPassword("testPassword")
                .nickname("testNickname")
                .memberName("testName")
                .memberNumber("010-0909-0909")
                .memberEmail("wodms6199@gmail.com")
                .memberDate("2000.01.01")
                .joinDate("2024.04.12")
                .gradeId(1)
                .reportNum(0)
                .memberStatus("Y")
                .build();
        memberRepository.save(testMember);
    }

    @Test
    @Transactional
    @DisplayName("정상 회원가입 테스트")
    public void registUserTest() {
        Member member = Member.builder()
                .userId("userId1")
                .userPassword("userPassword")
                .nickname("userNickname1")
                .memberName("userName1")
                .memberNumber("010-1234-5390")
                .memberEmail("testEmail1@gmail.com")
                .memberDate("2000.01.01")
                .build();

        Member registUser = memberService.registMember(member);

        Member findUser = memberService.findMemberById(registUser.getMemberId());

        assertThat(registUser).isEqualTo(findUser);

    }

    @Test
    @Transactional
    @DisplayName("중복 회원가입 테스트")
    public void registDuplicateMember() {
        Member member = Member.builder()
                .userId("testUser")
                .userPassword("testPassword")
                .nickname("testNickname")
                .memberName("testName")
                .memberNumber("010-0909-0909")
                .memberEmail("testEmail@gmail.com")
                .memberDate("2000.01.01")
                .build();

        assertThatThrownBy(() -> memberService.registMember(member))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("Duplicate entry");
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    @Transactional
    public void loginSuccessTest() {
        String userId = "testUser";
        Member loginMember = memberService.sessionLogin(userId);
        assertThat(loginMember).isNotNull();
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    @Transactional
    public void loginFailTest() {
        String userId = "loginFailUser";
        assertThatThrownBy(() -> memberService.sessionLogin(userId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("사용자를 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("회원 탈퇴 테스트")
    @Transactional
    public void successWithdrawalMember() {
        int userId = 1;
        Member member = memberService.deleteMember(userId);

        assertThat(member.getWithdrawalDate()).isNotNull();
    }

    @Test
    @DisplayName("회원 탈퇴 실패 테스트")
    @Transactional
    public void failWithdrawalMember() {
        int nonExistentMemberId = 999999;

        assertThatThrownBy(() -> memberService.deleteMember(nonExistentMemberId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("존재하지 않는 회원입니다.");
    }

    @Test
    @DisplayName("회원 정보 수정 테스트")
    @Transactional
    public void successModifyMember() {
        int memberId = 1;
        MemberDTO modifyInfo = MemberDTO.builder()
                .memberEmail("modifyEmail@test.com")
                .memberNumber("011-1234-1234")
                .nickname("닉네임변경")
                .userPassword("modifyPassword")
                .build();
        Member modifyMember = memberService.modifyMember(memberId, modifyInfo);
        assertThat(modifyMember.getMemberEmail()).isEqualTo(modifyInfo.getMemberEmail());
        assertThat(modifyMember.getMemberNumber()).isEqualTo(modifyInfo.getMemberNumber());
        assertThat(modifyMember.getNickname()).isEqualTo(modifyInfo.getNickname());
        assertThat(modifyMember.getUserPassword()).isEqualTo(modifyInfo.getUserPassword());
    }

    @Test
    @DisplayName("회원 정보 수정 실패 테스트")
    @Transactional
    public void failModifyMember() {
        int nonExistentMemberId = 99999;
        MemberDTO modifyInfo = MemberDTO.builder()
                .memberEmail("modifyEmail@test.com")
                .memberNumber("011-1234-1234")
                .nickname("닉네임변경")
                .userPassword("modifyPassword")
                .build();
        assertThatThrownBy(() -> memberService.modifyMember(nonExistentMemberId, modifyInfo))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("존재하지 않는 회원입니다.");
    }

    @Test
    @DisplayName("단일 회원 조회 성공 테스트")
    @Transactional
    public void successFindMemberById() {
        Member member = Member.builder()
                .userId("userId12")
                .userPassword("userPassword12")
                .nickname("userNickname12")
                .memberName("userName12")
                .memberNumber("010-9934-5390")
                .memberEmail("testEmail12@gmail.com")
                .memberDate("2001.01.01")
                .build();

        Member registUser = memberService.registMember(member);

        Member testUser = memberService.findMemberById(registUser.getMemberId());

        assertThat(registUser.getUserId()).isEqualTo(testUser.getUserId());
        assertThat(registUser.getUserPassword()).isEqualTo(testUser.getUserPassword());
        assertThat(registUser.getNickname()).isEqualTo(testUser.getNickname());
        assertThat(registUser.getMemberName()).isEqualTo(testUser.getMemberName());
        assertThat(registUser.getMemberNumber()).isEqualTo(testUser.getMemberNumber());
        assertThat(registUser.getMemberEmail()).isEqualTo(testUser.getMemberEmail());
        assertThat(registUser.getMemberDate()).isEqualTo(testUser.getMemberDate());
    }

    @Test
    @DisplayName("단일 회원 조회 실패 테스트")
    @Transactional
    public void failFindMemberById() {
        int nonExistentMemberId = 99999;
        assertThatThrownBy(() -> memberService.findMemberById(nonExistentMemberId))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("회원을 찾지 못했습니다");
    }

    @Test
    @DisplayName("비밀번호 변경 성공 테스트")
    @Transactional
    public void successChangePassword() {
        Member member = memberService.findMemberById(2);

        String beforeChangePassword = member.getUserPassword();
        String memberEmail = member.getMemberEmail();

        EmailMessage emailMessage = EmailMessage.builder()
                .to("wodms6199@gmail.com")
                .build();
        emailService.sendMail(emailMessage);

        String afterChangePassword = member.getUserPassword();

        assertThat(memberEmail).isEqualTo("yunjaeeun12@naver.com");
        assertThat(beforeChangePassword).isNotEqualTo(afterChangePassword);
    }

    /* 실행 안됨. 수정예정 */
//    @Test
//    @DisplayName("비밀번호 변경 실패 테스트")
//    @Transactional
//    public void failChangePassword() {
//        EmailMessage nonExistentEmail = EmailMessage.builder()
//                .to("test1231231@tset.com")
//                .build();
//
//        assertThatThrownBy(() -> emailService.sendMail(nonExistentEmail))
//                .isInstanceOf(ChangeSetPersister.NotFoundException.class)
//                .hasMessageContaining("회원 이메일을 찾을 수 없습니다.");
//    }
}
