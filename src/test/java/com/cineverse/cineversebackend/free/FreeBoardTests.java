package com.cineverse.cineversebackend.free;

import com.cineverse.cineversebackend.free.board.dto.FreeBoardDTO;
import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import com.cineverse.cineversebackend.free.board.repo.FreeBoardRepository;
import com.cineverse.cineversebackend.free.board.service.FreeBoardService;
import com.cineverse.cineversebackend.user.member.entity.Member;
import com.cineverse.cineversebackend.user.member.repo.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FreeBoardTests {
    private final FreeBoardService freeBoardService;
    private final FreeBoardRepository freeBoardRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper mapper;

    @Autowired
    public FreeBoardTests(FreeBoardService freeBoardService,
                          FreeBoardRepository freeBoardRepository,
                          MemberRepository memberRepository,
                          ModelMapper mapper) {
        this.freeBoardService = freeBoardService;
        this.freeBoardRepository = freeBoardRepository;
        this.memberRepository = memberRepository;
        this.mapper = mapper;
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
    }

    @Test
    @Transactional
    @DisplayName("게시글 생성 성공 테스트")
    public void successRegistFreeBoard() {
        Optional<Member> optionalMember = memberRepository.findById(1);
        Member member = optionalMember.get();

        FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder()
                .freeTitle("자유 제목")
                .freeContent("게시글 내용")
                .member(member)
                .build();
        MultipartFile[] images = new MultipartFile[0];

        FreeBoard freeBoard = freeBoardService.registFree(freeBoardDTO, images);

        assertThat(freeBoard.getFreeTitle()).isEqualTo(freeBoardDTO.getFreeTitle());
        assertThat(freeBoard.getFreeContent()).isEqualTo(freeBoardDTO.getFreeContent());
        assertThat(freeBoard.getFreeDate()).isNotNull();
        assertThat(freeBoard.getFreeViewCount()).isEqualTo(0);
        assertThat(freeBoard.getMember()).isEqualTo(freeBoardDTO.getMember());
    }

    @Test
    @Transactional
    @DisplayName("게시글 수정 성공 테스트")
    public void successModifyFreeBoard() {
        FreeBoardDTO modifyInfo = FreeBoardDTO.builder()
                .freeTitle("자유 제목")
                .freeContent("게시글 내용")
                .build();
        MultipartFile[] images = new MultipartFile[0];

        FreeBoard modifiedFreeBoard = freeBoardService.modifyFree(1, modifyInfo, images);

        assertThat(modifiedFreeBoard.getFreeTitle()).isEqualTo(modifyInfo.getFreeTitle());
        assertThat(modifiedFreeBoard.getFreeContent()).isEqualTo(modifyInfo.getFreeContent());
    }

    @Test
    @Transactional
    @DisplayName("게시글 삭제 성공 테스트")
    public void successDeleteFreeBoard() {
        Optional<FreeBoard> optionalFreeBoard = freeBoardRepository.findById(1);
        FreeBoard freeBoard = optionalFreeBoard.get();
        assertThat(freeBoard.getFreeDeleteDate()).isNull();

        FreeBoard deletedFreeBoard = freeBoardService.modifyFreeDeleteDate(1);
        assertThat(deletedFreeBoard.getFreeDeleteDate()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("게시글 전체조회 성공 테스트")
    public void suceessFindAllBoards() {
        List<FreeBoard> freeBoards = freeBoardService.findFreeList();

        assertThat(freeBoards).isNotEmpty();
    }

    @Test
    @Transactional
    @DisplayName("게시글 단일조회 성공 테스트")
    public void successFindFreeBoardById() {
        Optional<FreeBoard> optionalFreeBoard = freeBoardRepository.findById(1);
        FreeBoard freeBoard = optionalFreeBoard.get();

        FreeBoardDTO selectedBoard = freeBoardService.findFreeById(1);

        assertThat(freeBoard.getFreeTitle()).isEqualTo(selectedBoard.getFreeTitle());
        assertThat(freeBoard.getFreeContent()).isEqualTo(selectedBoard.getFreeContent());
        assertThat(freeBoard.getMember()).isEqualTo(selectedBoard.getMember());
        assertThat(freeBoard.getFreeDate()).isEqualTo(selectedBoard.getFreeDate());
        assertThat(freeBoard.getFreeViewCount()).isEqualTo(selectedBoard.getFreeViewCount());
    }
}
