package com.cineverse.cineversebackend.event.board.controller;

import com.cineverse.cineversebackend.event.board.dto.EventBoardDTO;
import com.cineverse.cineversebackend.event.board.dto.quiz.QuizDTO;
import com.cineverse.cineversebackend.event.board.dto.quiz.QuizSolverDTO;
import com.cineverse.cineversebackend.event.board.dto.vote.VoteContentDTO;
import com.cineverse.cineversebackend.event.board.dto.vote.VoteDTO;
//import com.cineverse.cineversebackend.event.board.dto.vote.VoterDTO;
import com.cineverse.cineversebackend.event.board.entity.EventBoard;
import com.cineverse.cineversebackend.event.board.entity.quiz.QuizSolver;
import com.cineverse.cineversebackend.event.board.service.event.EventBoardService;
import com.cineverse.cineversebackend.event.board.service.quiz.QuizService;
import com.cineverse.cineversebackend.event.board.service.vote.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// 젠킨스 테스트 주석
@RestController
@RequestMapping("/event_board")
public class EventBoardController {

    private final EventBoardService eventBoardService;
    private final QuizService quizService;
    private final VoteService voteService;

    @Autowired
    public EventBoardController(EventBoardService eventBoardService,
                                QuizService quizService,
                                VoteService voteService) {
        this.eventBoardService = eventBoardService;
        this.quizService = quizService;
        this.voteService = voteService;
    }

//  테스트용 주석 추가
    @GetMapping("/health_check")
    public String health() {
        return "fine";
    }

    /* 설명. 이벤트 게시글 작성 (기본) */
    @PostMapping("/regist")
    public ResponseEntity<EventBoardDTO> test (@RequestBody EventBoardDTO newEvent) {
        eventBoardService.registEvent(newEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEvent);
    }

    /* 설명. 이벤트 게시글 수정 */
    @PatchMapping("/modify/{eventId}")
    public ResponseEntity<EventBoard> modifyEvent(@RequestBody EventBoardDTO event, @PathVariable int eventId) {
        return ResponseEntity.ok(eventBoardService.modifyEvent(eventId, event));
    }

    /* 설명. 이벤트 게시글 삭제*/
    @PatchMapping("/delete/{eventId}")
    public ResponseEntity<EventBoard> deleteEvent(@PathVariable int eventId) {
        return ResponseEntity.ok(eventBoardService.deleteEvent(eventId));
    }

    /* 설명. 이벤트 게시글 전체 조회 */
    @GetMapping("/list")
    public List<EventBoard> findEventList() {
        List<EventBoard> eventList = eventBoardService.findEventList();

        return eventList;
    }

    /* 설명. 이벤트 게시글 단일 조회 */
    @GetMapping("{eventId}")
    public EventBoardDTO findEventById(@PathVariable int eventId) {
        EventBoardDTO event = eventBoardService.findEventById(eventId);

        return event;
    }

    /* 설명. 퀴즈 답안 제출 */
    @PostMapping("/quiz/submit")
    public ResponseEntity<QuizSolver> submitQuizAnswer(@RequestBody QuizSolverDTO quizSolverDTO) {
        quizService.submitAnswer(quizSolverDTO);
        return ResponseEntity.ok().build();
    }

    /* 설명. 퀴즈별 정답률 계산(게시글 조회 안에 넣어야함) */
    @GetMapping("/correct_rate")
    public ResponseEntity<List<QuizDTO>> getQuizCorrectRates() {
        List<QuizDTO> quizDTOList = quizService.calculateCorrectRate();

        return ResponseEntity.ok(quizDTOList);
    }

//    /* 설명. 투표 제출 */
//    @PostMapping("/vote/submit")
//    public ResponseEntity<?> submitVote(@RequestBody VoterDTO voterDTO) {
//        voteService.submitVote(voterDTO);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
