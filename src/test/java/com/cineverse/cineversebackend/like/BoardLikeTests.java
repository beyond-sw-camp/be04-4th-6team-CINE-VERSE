package com.cineverse.cineversebackend.like;

import com.cineverse.cineversebackend.event.board.repo.EventBoardRepository;
import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import com.cineverse.cineversebackend.free.board.repo.FreeBoardRepository;
import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
import com.cineverse.cineversebackend.info.board.entity.InfoCategory;
import com.cineverse.cineversebackend.info.board.repo.InfoBoardRepository;
import com.cineverse.cineversebackend.like.service.PostLikeService;
import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
import com.cineverse.cineversebackend.review.board.entity.ReviewCategory;
import com.cineverse.cineversebackend.review.board.repo.ReviewBoardRepository;
import com.cineverse.cineversebackend.user.member.entity.Member;
import com.cineverse.cineversebackend.user.member.repo.MemberRepository;
import com.cineverse.cineversebackend.user.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
// 젠킨스 테스트
@SpringBootTest
public class BoardLikeTests {
    private final PostLikeService postLikeService;
    private final ReviewBoardRepository reviewBoardRepository;
    private final FreeBoardRepository freeBoardRepository;
    private final EventBoardRepository eventBoardRepository;
    private final InfoBoardRepository infoBoardRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public BoardLikeTests(PostLikeService postLikeService,
                          ReviewBoardRepository reviewBoardRepository,
                          FreeBoardRepository freeBoardRepository,
                          EventBoardRepository eventBoardRepository,
                          InfoBoardRepository infoBoardRepository,
                          MemberRepository memberRepository) {
        this.postLikeService = postLikeService;
        this.reviewBoardRepository = reviewBoardRepository;
        this.freeBoardRepository = freeBoardRepository;
        this.eventBoardRepository = eventBoardRepository;
        this.infoBoardRepository = infoBoardRepository;
        this.memberRepository = memberRepository;
    }

    @BeforeEach
    public void setup() {
        Optional<Member> optionalMember = memberRepository.findById(1);
        Member member = optionalMember.get();
        ReviewCategory reviewCategory = ReviewCategory.builder()
                .reviewCategoryId(1)
                .reviewCategory("테스트 카테고리")
                .build();

        ReviewBoard reviewBoard = ReviewBoard.builder()
                .reviewTitle("리뷰 제목")
                .reviewContent("리뷰 내용")
                .reviewViewCount(0)
                .reviewDate("2024.04.14")
                .reviewStar(5)
                .member(member)
                .reviewCategory(reviewCategory)
                .build();
        reviewBoardRepository.save(reviewBoard);

        FreeBoard freeBoard = FreeBoard.builder()
                .freeTitle("자유 제목")
                .freeContent("게시글 내용")
                .freeDate("2024.04.14")
                .freeViewCount(0)
                .member(member)
                .build();
        freeBoardRepository.save(freeBoard);

        InfoCategory infoCategory = InfoCategory.builder()
                .infoCategoryId(1)
                .infoCategory("테스트 카테고리")
                .build();

        InfoBoard infoBoard = InfoBoard.builder()
                .infoTitle("게시글 제목")
                .infoContent("게시글 내용")
                .infoViewCount(0)
                .infoDate("2024.04.14")
                .member(member)
                .infoCategory(infoCategory)
                .build();
        infoBoardRepository.save(infoBoard);
    }
}
