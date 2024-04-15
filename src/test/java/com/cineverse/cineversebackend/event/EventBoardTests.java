package com.cineverse.cineversebackend.event;

import com.cineverse.cineversebackend.event.board.dto.EventBoardDTO;
import com.cineverse.cineversebackend.event.board.dto.quiz.QuizDTO;
import com.cineverse.cineversebackend.event.board.entity.EventBoard;
import com.cineverse.cineversebackend.event.board.entity.quiz.Quiz;
import com.cineverse.cineversebackend.event.board.repo.EventBoardRepository;
import com.cineverse.cineversebackend.event.board.repo.quiz.QuizRepository;
import com.cineverse.cineversebackend.event.board.repo.quiz.QuizSolverRepository;
import com.cineverse.cineversebackend.event.board.service.event.EventBoardService;
import com.cineverse.cineversebackend.user.member.entity.Member;
import com.cineverse.cineversebackend.user.member.repo.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EventBoardTests {
    private final EventBoardRepository eventBoardRepository;
    private final QuizRepository quizRepository;
    private final QuizSolverRepository quizSolverRepository;
    private final MemberRepository memberRepository;
    private final EventBoardService eventBoardService;

    @Autowired
    public EventBoardTests(EventBoardRepository eventBoardRepository,
                           QuizRepository quizRepository,
                           QuizSolverRepository quizSolverRepository,
                           MemberRepository memberRepository,
                           EventBoardService eventBoardService) {
        this.eventBoardRepository = eventBoardRepository;
        this.quizRepository = quizRepository;
        this.quizSolverRepository = quizSolverRepository;
        this.memberRepository = memberRepository;
        this.eventBoardService = eventBoardService;
    }

    @Test
    @Transactional
    @DisplayName("이벤트 게시글 작성 성공 테스트")
    public void successRegistEventBoardTest() {
        Optional<Member> optionalMember = memberRepository.findById(1);
        Member member = optionalMember.get();

        QuizDTO quiz = QuizDTO.builder()
                .quizHint("퀴즈 힌트")
                .quizAnswer("퀴즈 질문")
                .quizQuestion("퀴즈 응답")

                .build();

        EventBoardDTO eventBoardDTO = EventBoardDTO.builder()
                .eventContent("게시글 내용")
                .eventTitle("게시글 제목")
                .member(member)
                .quiz(quiz)
                .build();

        EventBoard eventBoard = eventBoardService.registEvent(eventBoardDTO);

//        assertThat()
    }
}
