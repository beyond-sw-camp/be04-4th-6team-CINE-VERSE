//package com.cineverse.cineversebackend.info;
//
//import com.cineverse.cineversebackend.info.board.dto.InfoBoardDTO;
//import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
//import com.cineverse.cineversebackend.info.board.entity.InfoCategory;
//import com.cineverse.cineversebackend.info.board.repo.InfoBoardRepository;
//import com.cineverse.cineversebackend.info.board.service.InfoBoardService;
//import com.cineverse.cineversebackend.review.board.dto.ReviewBoardDTO;
//import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
//import com.cineverse.cineversebackend.user.member.entity.Member;
//import com.cineverse.cineversebackend.user.member.repo.MemberRepository;
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
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class infoBoardTests {
//    private final MemberRepository memberRepository;
//    private final InfoBoardRepository infoBoardRepository;
//    private final InfoBoardService infoBoardService;
//    private final ModelMapper mapper;
//
//    @Autowired
//    public infoBoardTests(MemberRepository memberRepository,
//                          InfoBoardRepository infoBoardRepository,
//                          InfoBoardService infoBoardService,
//                          ModelMapper mapper) {
//        this.memberRepository = memberRepository;
//        this.infoBoardRepository = infoBoardRepository;
//        this.infoBoardService = infoBoardService;
//        this.mapper = mapper;
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
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("정보 게시글 작성 성공 테스트")
//    public void successRegistInfoBoardTest() {
//        Optional<Member> optionalMember = memberRepository.findById(1);
//        Member member = optionalMember.get();
//        InfoCategory infoCategory = InfoCategory.builder()
//                .infoCategoryId(1)
//                .infoCategory("테스트 카테고리")
//                .build();
//
//        InfoBoardDTO infoBoardDTO = InfoBoardDTO.builder()
//                .infoTitle("작성 테스트")
//                .infoContent("작성 테스트 네용")
//                .infoViewCount(0)
//                .member(member)
//                .infoCategory(infoCategory)
//                .build();
//
//        MultipartFile[] images = new MultipartFile[0];
//
//        InfoBoard infoBoard = infoBoardService.registInfo(infoBoardDTO, images);
//
//        assertThat(infoBoard.getInfoTitle()).isEqualTo(infoBoardDTO.getInfoTitle());
//        assertThat(infoBoard.getInfoContent()).isEqualTo(infoBoardDTO.getInfoContent());
//        assertThat(infoBoard.getInfoViewCount()).isEqualTo(infoBoardDTO.getInfoViewCount());
//        assertThat(infoBoard.getInfoDate()).isNotNull();
//        assertThat(infoBoard.getInfoDeleteDate()).isNull();
//        assertThat(infoBoard.getInfoCategory()).isEqualTo(infoBoardDTO.getInfoCategory());
//        assertThat(infoBoard.getMember()).isEqualTo(infoBoardDTO.getMember());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("게시글 수정 성공 테스트")
//    public void successModifyBoardTest() {
//        InfoCategory infoCategory = InfoCategory.builder()
//                .infoCategoryId(2)
//                .infoCategory("카테고리2")
//                .build();
//
//
//        InfoBoardDTO modifyInfos = InfoBoardDTO.builder()
//                .infoTitle("제목 수정 테스트")
//                .infoContent("내용 수정 테스트")
//                .infoCategory(infoCategory)
//                .build();
//
//        MultipartFile[] images = new MultipartFile[0];
//
//        InfoBoard modifiedBoard = infoBoardService.modifyInfo(1, modifyInfos, images);
//
//        assertThat(modifiedBoard.getInfoTitle()).isEqualTo(modifyInfos.getInfoTitle());
//        assertThat(modifiedBoard.getInfoContent()).isEqualTo(modifyInfos.getInfoContent());
//        assertThat(modifiedBoard.getInfoCategory()).isEqualTo(modifyInfos.getInfoCategory());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("게시글 단일 조회 성공 테스트")
//    public void successFindBoardByIdTest() {
//        Optional<InfoBoard> optionalInfoBoard = infoBoardRepository.findById(1);
//        InfoBoard infoBoard = optionalInfoBoard.get();
//
//        InfoBoardDTO findBoard = infoBoardService.findInfoById(1);
//
//        assertThat(infoBoard.getInfoTitle()).isEqualTo(findBoard.getInfoTitle());
//        assertThat(infoBoard.getInfoContent()).isEqualTo(findBoard.getInfoContent());
//        assertThat(infoBoard.getInfoViewCount()).isEqualTo(findBoard.getInfoViewCount());
//        assertThat(infoBoard.getInfoDate()).isNotNull();
//        assertThat(infoBoard.getInfoDeleteDate()).isNull();
//        assertThat(infoBoard.getInfoCategory()).isEqualTo(findBoard.getInfoCategory());
//        assertThat(infoBoard.getMember()).isEqualTo(findBoard.getMember());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("게시글 전체 조회 성공 테스트")
//    public void successFindAllBoards() {
//        List<InfoBoard> boards = infoBoardRepository.findAll();
//
//        assertThat(boards).isNotEmpty();
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("게시글 삭제 성공 테스트")
//    public void successDeleteBoardTest() {
//        Optional<InfoBoard> optionalInfoBoard = infoBoardRepository.findById(1);
//        InfoBoard infoBoard = optionalInfoBoard.get();
//        assertThat(infoBoard.getInfoDeleteDate()).isNull();
//
//        InfoBoard deletedBoard = infoBoardService.deleteInfo(1);
//        assertThat(deletedBoard.getInfoDeleteDate()).isNotNull();
//    }
//}
