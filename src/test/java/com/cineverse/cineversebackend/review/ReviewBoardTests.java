//package com.cineverse.cineversebackend.review;
//
//import com.cineverse.cineversebackend.review.board.dto.ReviewBoardDTO;
//import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
//import com.cineverse.cineversebackend.review.board.entity.ReviewCategory;
//import com.cineverse.cineversebackend.review.board.repo.ReviewBoardRepository;
//import com.cineverse.cineversebackend.review.board.service.ReviewBoardService;
//import com.cineverse.cineversebackend.user.member.entity.Member;
//import com.cineverse.cineversebackend.user.member.repo.MemberRepository;
//import com.cineverse.cineversebackend.user.member.service.MemberService;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.as;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class ReviewBoardTests {
//
//    private final ReviewBoardService reviewBoardService;
//    private final ReviewBoardRepository reviewBoardRepository;
//    private final MemberRepository memberRepository;
//    private final ModelMapper mapper;
//
//    @Autowired
//    public ReviewBoardTests(ReviewBoardService reviewBoardService,
//                            ReviewBoardRepository reviewBoardRepository,
//                            MemberRepository memberRepository,
//                            ModelMapper mapper) {
//        this.reviewBoardService = reviewBoardService;
//        this.reviewBoardRepository = reviewBoardRepository;
//        this.memberRepository = memberRepository;
//        this.mapper = mapper;
//    }
//
//    @BeforeEach
//    public void setup() {
//        Optional<Member> optionalMember = memberRepository.findById(1);
//        Member member = optionalMember.get();
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
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("리뷰 게시글 작성 성공 테스트")
//    public void successRegistReviewBoardTest() {
//        Optional<Member> optionalMember = memberRepository.findById(1);
//        Member member = optionalMember.get();
//        ReviewCategory reviewCategory = ReviewCategory.builder()
//                .reviewCategoryId(1)
//                .reviewCategory("테스트 카테고리")
//                .build();
//
//        ReviewBoard reviewBoard = ReviewBoard.builder()
//                .reviewTitle("작성 테스트")
//                .reviewContent("작성 테스트 내용")
//                .reviewViewCount(0)
//                .reviewStar(5)
//                .member(member)
//                .reviewCategory(reviewCategory)
//                .build();
//        MultipartFile[] images = new MultipartFile[0];
//
//        ReviewBoardDTO reviewBoardDTO = mapper.map(reviewBoard, ReviewBoardDTO.class);
//
//        ReviewBoard registedReview = reviewBoardService.registReview(reviewBoardDTO, images);
//
//        assertThat(registedReview.getReviewTitle()).isEqualTo(reviewBoard.getReviewTitle());
//        assertThat(registedReview.getReviewContent()).isEqualTo(reviewBoard.getReviewContent());
//        assertThat(registedReview.getReviewViewCount()).isEqualTo(reviewBoard.getReviewViewCount());
//        assertThat(registedReview.getReviewStar()).isEqualTo(reviewBoard.getReviewStar());
//        assertThat(registedReview.getMember()).isEqualTo(reviewBoard.getMember());
//        assertThat(registedReview.getReviewCategory()).isEqualTo(reviewBoard.getReviewCategory());
//        assertThat(registedReview.getReviewDeleteDate()).isNull();
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("리뷰 단일조회 성공 테스트")
//    public void successFindReviewByIdTest() {
//        Optional<ReviewBoard> optionalReviewBoard = reviewBoardRepository.findById(1);
//        ReviewBoard reviewBoard = optionalReviewBoard.get();
//
//        ReviewBoardDTO findReview = reviewBoardService.findReviewById(1);
//
//        assertThat(reviewBoard.getReviewTitle()).isEqualTo(findReview.getReviewTitle());
//        assertThat(reviewBoard.getReviewContent()).isEqualTo(findReview.getReviewContent());
//        assertThat(reviewBoard.getReviewViewCount()).isEqualTo(findReview.getReviewViewCount());
//        assertThat(reviewBoard.getReviewStar()).isEqualTo(findReview.getReviewStar());
//        assertThat(reviewBoard.getMember()).isEqualTo(findReview.getMember());
//        assertThat(reviewBoard.getReviewCategory()).isEqualTo(findReview.getReviewCategory());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("리뷰 전체조회 성공 테스트")
//    public void successFindAllReviewsTest() {
//        List<ReviewBoard> reviewBoards = reviewBoardService.findReivewList();
//
//        boolean test = false;
//
//        if (!reviewBoards.isEmpty()) {
//            test = true;
//        } else {
//            test = false;
//        }
//
//        assertThat(test).isEqualTo(true);
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("리뷰수정 성공 테스트")
//    public void successModifyReview() {
//        ReviewCategory reviewCategory = ReviewCategory.builder()
//                .reviewCategoryId(2)
//                .reviewCategory("수정 테스트")
//                .build();
//
//        ReviewBoardDTO modifyInfos = ReviewBoardDTO.builder()
//                .reviewTitle("제목 수정 테스트")
//                .reviewContent("내용 수정 테스트")
//                .reviewCategory(reviewCategory)
//                .reviewStar(3)
//                .build();
//
//        MultipartFile[] images = new MultipartFile[0];
//
//        ReviewBoard modifiedReview = reviewBoardService.modifyReview(1, modifyInfos, images);
//
//        assertThat(modifyInfos.getReviewTitle()).isEqualTo(modifiedReview.getReviewTitle());
//        assertThat(modifyInfos.getReviewStar()).isEqualTo(modifiedReview.getReviewStar());
//        assertThat(modifyInfos.getReviewContent()).isEqualTo(modifiedReview.getReviewContent());
//        assertThat(modifyInfos.getReviewCategory()).isEqualTo(modifiedReview.getReviewCategory());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("리뷰삭제 성공 테스트")
//    public void successDeleteReview() {
//        ReviewBoardDTO review = reviewBoardService.findReviewById(1);
//
//        ReviewBoard deleteReview = reviewBoardService.deleteReview(1);
//
//        assertThat(review.getReviewDeleteDate()).isNull();
//        assertThat(deleteReview.getReviewDeleteDate()).isNotNull();
//    }
//}
