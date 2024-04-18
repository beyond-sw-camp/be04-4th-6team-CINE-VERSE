package com.cineverse.cineversebackend.free;

import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import com.cineverse.cineversebackend.free.board.repo.FreeBoardRepository;
import com.cineverse.cineversebackend.free.comment.dto.FreeCommentDTO;
import com.cineverse.cineversebackend.free.comment.entity.FreeComment;
import com.cineverse.cineversebackend.free.comment.repo.FreeCommentRepository;
import com.cineverse.cineversebackend.free.comment.service.FreeCommentService;
import com.cineverse.cineversebackend.review.comment.entity.ReviewComment;
import com.cineverse.cineversebackend.user.member.entity.Member;
import com.cineverse.cineversebackend.user.member.repo.MemberRepository;
import com.cineverse.cineversebackend.user.member.service.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FreeCommentTests {
    private final MemberRepository memberRepository;
    private final FreeBoardRepository freeBoardRepository;
    private final FreeCommentRepository freeCommentRepository;
    private final FreeCommentService freeCommentService;

    @Autowired
    public FreeCommentTests(MemberRepository memberRepository,
                            FreeBoardRepository freeBoardRepository,
                            FreeCommentRepository freeCommentRepository,
                            FreeCommentService freeCommentService) {
        this.memberRepository = memberRepository;
        this.freeBoardRepository = freeBoardRepository;
        this.freeCommentRepository = freeCommentRepository;
        this.freeCommentService = freeCommentService;
    }

    @BeforeEach
    public void setup() {
        Optional<Member> optionalMember = memberRepository.findById(1);
        Member member = optionalMember.get();

        FreeBoard freeBoard = FreeBoard.builder()
                .freeTitle("자유 제목")
                .freeContent("게시글 내용")
                .freeDate("2024.04.14")
                .freeViewCount(0)
                .member(member)
                .build();
        freeBoardRepository.save(freeBoard);

        FreeComment freeComment = FreeComment.builder()
                .commentContent("댓글 내용")
                .commentDate("2024.04.14")
                .member(member)
                .freeId(1)
                .build();
        freeCommentRepository.save(freeComment);
    }

    @Test
    @Transactional
    @DisplayName("댓글 작성 성공 테스트")
    public void successRegistComment() {
        Optional<Member> optionalMember = memberRepository.findById(1);
        Member member = optionalMember.get();


        FreeComment freeComment = FreeComment.builder()
                .commentContent("댓글 내용")
                .commentDate("24.04.14")
                .member(member)
                .freeId(1)
                .build();

        FreeComment registedComment = freeCommentService.registFreeComment(freeComment);

        assertThat(registedComment.getCommentContent()).isEqualTo(freeComment.getCommentContent());
        assertThat(registedComment.getCommentDate()).isEqualTo(freeComment.getCommentDate());
        assertThat(registedComment.getMember()).isEqualTo(freeComment.getMember());
        assertThat(registedComment.getFreeId()).isEqualTo(freeComment.getFreeId());
        assertThat(registedComment.getCommentDeleteDate()).isNull();
    }

    @Test
    @Transactional
    @DisplayName("댓글 삭제 성공 테스트")
    public void successDeleteComment() {
        Optional<FreeComment> freeComment = freeCommentRepository.findById(1);
        FreeComment beforeDeleteComment = freeComment.get();
        assertThat(beforeDeleteComment.getCommentDeleteDate()).isNull();

        FreeComment deleteComment = freeCommentService.modifyFreeCommentDeleteDate(1);
        assertThat(deleteComment.getCommentDeleteDate()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("댓글 전체조회 성공 테스트")
    public void successFindAllComments() {
        List<FreeComment> freeComments = freeCommentService.findFreeCommentList();

        assertThat(freeComments).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("댓글 수정 성공 테스트")
    public void successModifyComment() {
        FreeCommentDTO modifyInfo = FreeCommentDTO.builder()
                .commentContent("수정 내용")
                .build();

        FreeComment modifiedComment = freeCommentService.modifyFreeComment(1, modifyInfo);

        assertThat(modifyInfo.getCommentContent()).isEqualTo(modifiedComment.getCommentContent());
    }
}
