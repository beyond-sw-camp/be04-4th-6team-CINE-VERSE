//package com.cineverse.cineversebackend.review;
//
//import com.cineverse.cineversebackend.free.comment.dto.FreeCommentDTO;
//import com.cineverse.cineversebackend.free.comment.entity.FreeComment;
//import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
//import com.cineverse.cineversebackend.review.board.entity.ReviewCategory;
//import com.cineverse.cineversebackend.review.board.repo.ReviewBoardRepository;
//import com.cineverse.cineversebackend.review.comment.dto.ReviewCommentDTO;
//import com.cineverse.cineversebackend.review.comment.entity.ReviewComment;
//import com.cineverse.cineversebackend.review.comment.repo.ReviewCommentRepository;
//import com.cineverse.cineversebackend.review.comment.service.ReviewCommentService;
//import com.cineverse.cineversebackend.review.comment.service.ReviewCommentServiceImpl;
//import com.cineverse.cineversebackend.user.member.entity.Member;
//import com.cineverse.cineversebackend.user.member.repo.MemberRepository;
//import com.cineverse.cineversebackend.user.member.service.MemberService;
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
//public class ReviewCommentTests {
//
//    private final MemberRepository memberRepository;
//    private final ReviewCommentRepository reviewCommentRepository;
//    private final ReviewCommentService reviewCommentService;
//    private final ReviewBoardRepository reviewBoardRepository;
//
//    @Autowired
//    public ReviewCommentTests(MemberRepository memberRepository,
//                              ReviewCommentRepository reviewCommentRepository,
//                              ReviewCommentService reviewCommentService,
//                              ReviewBoardRepository reviewBoardRepository) {
//        this.memberRepository = memberRepository;
//        this.reviewCommentRepository = reviewCommentRepository;
//        this.reviewCommentService = reviewCommentService;
//        this.reviewBoardRepository = reviewBoardRepository;
//    }
//
//    @BeforeEach
//    public void setup() {
//        Optional<Member> optionalMember = memberRepository.findById(4);
//        Member member = optionalMember.get();
//
//        ReviewCategory reviewCategory = ReviewCategory.builder()
//                .reviewCategoryId(1)
//                .reviewCategory("테스트 카테고리")
//                .build();
//
//        ReviewBoard reviewBoard = ReviewBoard.builder()
//                .reviewTitle("리뷰 제목")
//                .reviewContent("리뷰 내용")
//                .reviewViewCount(0)
//                .reviewDate("2024.04.14")
//                .reviewStar(5)
//                .member(member)
//                .reviewCategory(reviewCategory)
//                .build();
//        reviewBoardRepository.save(reviewBoard);
//
//        ReviewComment reviewComment = ReviewComment.builder()
//                .commentContent("댓글 내용")
//                .commentDate("24.04.14")
//                .member(member)
//                .reviewId(1)
//                .build();
//        reviewCommentRepository.save(reviewComment);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("댓글 작성 성공 테스트")
//    public void successRegistComment() {
//        Optional<Member> optionalMember = memberRepository.findById(1);
//        Member member = optionalMember.get();
//
//        ReviewComment reviewComment = ReviewComment.builder()
//                .commentContent("댓글 내용")
//                .commentDate("24.04.14")
//                .member(member)
//                .reviewId(1)
//                .build();
//
//        ReviewComment registedComment = reviewCommentService.registReviewComment(reviewComment);
//
//        assertThat(registedComment.getCommentContent()).isEqualTo(reviewComment.getCommentContent());
//        assertThat(registedComment.getCommentDate()).isEqualTo(reviewComment.getCommentDate());
//        assertThat(registedComment.getMember()).isEqualTo(reviewComment.getMember());
//        assertThat(registedComment.getReviewId()).isEqualTo(reviewComment.getReviewId());
//        assertThat(registedComment.getCommentDeleteDate()).isNull();
//    }
//
//
//
//    @Test
//    @Transactional
//    @DisplayName("댓글 삭제 성공 테스트")
//    public void successDeleteComment() {
//        Optional<ReviewComment> reviewComment = reviewCommentRepository.findById(1);
//        ReviewComment beforeDeleteComment = reviewComment.get();
//        assertThat(beforeDeleteComment.getCommentDeleteDate()).isNull();
//
//        ReviewComment deleteComment = reviewCommentService.modifyReviewCommentDeleteDate(1);
//        assertThat(deleteComment.getCommentDeleteDate()).isNotNull();
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("댓글 전체조회 성공 테스트")
//    public void successFindAllComments() {
//        List<ReviewComment> reviewComments = reviewCommentService.findReviewCommentList();
//
//        boolean test = false;
//
//        if (!reviewComments.isEmpty()) {
//            test = true;
//        } else {
//            test = false;
//        }
//
//        assertThat(test).isEqualTo(true);
//        assertThat(reviewComments).isNotEmpty();
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("댓글 수정 성공 테스트")
//    public void successModifyComment() {
//        ReviewCommentDTO modifyInfo = ReviewCommentDTO.builder()
//                .commentContent("수정 내용")
//                .build();
//
//        ReviewComment modifiedComment = reviewCommentService.modifyReviewComment(1, modifyInfo);
//
//        assertThat(modifyInfo.getCommentContent()).isEqualTo(modifiedComment.getCommentContent());
//    }
//}
