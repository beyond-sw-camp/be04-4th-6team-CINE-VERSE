//package com.cineverse.cineversebackend.info;
//
//import com.cineverse.cineversebackend.free.comment.entity.FreeComment;
//import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
//import com.cineverse.cineversebackend.info.board.entity.InfoCategory;
//import com.cineverse.cineversebackend.info.board.repo.InfoBoardRepository;
//import com.cineverse.cineversebackend.info.comment.entity.InfoComment;
//import com.cineverse.cineversebackend.info.comment.repo.InfoCommentRepository;
//import com.cineverse.cineversebackend.info.comment.service.InfoCommentService;
//import com.cineverse.cineversebackend.user.member.entity.Member;
//import com.cineverse.cineversebackend.user.member.repo.MemberRepository;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class infoCommentTests {
//    private final MemberRepository memberRepository;
//    private final InfoBoardRepository infoBoardRepository;
//    private final InfoCommentRepository infoCommentRepository;
//    private final InfoCommentService infoCommentService;
//
//    @Autowired
//    public infoCommentTests(MemberRepository memberRepository,
//                            InfoBoardRepository infoBoardRepository,
//                            InfoCommentRepository infoCommentRepository,
//                            InfoCommentService infoCommentService) {
//        this.memberRepository = memberRepository;
//        this.infoBoardRepository = infoBoardRepository;
//        this.infoCommentRepository = infoCommentRepository;
//        this.infoCommentService = infoCommentService;
//    }
//
//    @BeforeEach
//    public void setup() {
//        Optional<Member> optionalMember = memberRepository.findById(1);
//        Member member = optionalMember.get();
//        InfoCategory infoCategory = InfoCategory.builder()
//                .infoCategoryId(1)
//                .infoCategory("테스트 카테고리")
//                .build();
//
//        InfoBoard infoBoard = InfoBoard.builder()
//                .infoTitle("게시글 제목")
//                .infoContent("게시글 내용")
//                .infoViewCount(0)
//                .infoDate("2024.04.14")
//                .member(member)
//                .infoCategory(infoCategory)
//                .build();
//        infoBoardRepository.save(infoBoard);
//
//        InfoComment infoComment = InfoComment.builder()
//                .commentContent("댓글 내용")
//                .commentDate("2024.04.14")
//                .member(member)
//                .infoId(1)
//                .build();
//        infoCommentRepository.save(infoComment);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("댓글 작성 성공 테스트")
//    public void successRegistComment() {
//        Optional<Member> optionalMember = memberRepository.findById(1);
//        Member member = optionalMember.get();
//
//
//        InfoComment comment = InfoComment.builder()
//                .commentContent("댓글 내용")
//                .commentDate("24.04.14")
//                .member(member)
//                .infoId(1)
//                .build();
//
//        InfoComment registedComment = infoCommentService.registInfoComment(comment);
//
//        assertThat(registedComment.getCommentContent()).isEqualTo(comment.getCommentContent());
//        assertThat(registedComment.getCommentDate()).isEqualTo(comment.getCommentDate());
//        assertThat(registedComment.getMember()).isEqualTo(comment.getMember());
//        assertThat(registedComment.getInfoId()).isEqualTo(comment.getInfoId());
//        assertThat(registedComment.getCommentDeleteDate()).isNull();
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("댓글 삭제 성공 테스트")
//    public void successDeleteComment() {
//        Optional<InfoComment> optionalInfoComment = infoCommentRepository.findById(1);
//        InfoComment comment = optionalInfoComment.get();
//        assertThat(comment.getCommentDeleteDate()).isNull();
//
//        InfoComment deletedComment = infoCommentService.modifyInfoCommentDeleteDate(1);
//        assertThat(deletedComment.getCommentDeleteDate()).isNotNull();
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("댓글 전체조회 성공 테스트")
//    public void successFindAllComments() {
//        List<InfoComment> comments = infoCommentService.findInfoCommentList();
//
//        assertThat(comments).isNotEmpty();
//    }
//}
