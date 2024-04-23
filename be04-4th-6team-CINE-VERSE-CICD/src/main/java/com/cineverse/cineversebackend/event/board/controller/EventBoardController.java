package com.cineverse.cineversebackend.event.board.controller;

import com.cineverse.cineversebackend.event.board.dto.EventBoardDTO;
import com.cineverse.cineversebackend.event.board.dto.quiz.QuizDTO;
import com.cineverse.cineversebackend.event.board.dto.quiz.QuizSolverDTO;
import com.cineverse.cineversebackend.event.board.dto.vote.ReadyVoterDTO;
import com.cineverse.cineversebackend.event.board.dto.vote.VoteContentDTO;
import com.cineverse.cineversebackend.event.board.dto.vote.VoteDTO;
//import com.cineverse.cineversebackend.event.board.dto.vote.VoterDTO;
import com.cineverse.cineversebackend.event.board.entity.EventBoard;
import com.cineverse.cineversebackend.event.board.entity.quiz.QuizSolver;
import com.cineverse.cineversebackend.event.board.service.event.EventBoardService;
import com.cineverse.cineversebackend.event.board.service.quiz.QuizService;
import com.cineverse.cineversebackend.event.board.service.vote.VoteService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/health_check")
    @Operation(summary = "백엔드 통신 확인", description = "백엔드 통신 상태를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "500", description = "통신 오류")
    public String health() {
        return "I'm fine";
    }

    /* 설명. 이벤트 게시글 작성 (기본) */
    @PostMapping("/regist")
    @Operation(summary = "이벤트 게시글 작성", description = "이벤트 게시글을 작성합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<EventBoardDTO> test (
            @Parameter(required = true, description = "게시글 생성 요청")
            @RequestBody EventBoardDTO newEvent) {
        eventBoardService.registEvent(newEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEvent);
    }

    /* 설명. 이벤트 게시글 수정 */
    @PatchMapping("/modify/{eventId}")
    @Operation(summary = "이벤트 게시글 수정", description = "이벤트 게시글을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<EventBoard> modifyEvent(
            @Parameter(required = true, description = "게시글 수정 요청")
            @RequestBody EventBoardDTO event,
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int eventId) {
        return ResponseEntity.ok(eventBoardService.modifyEvent(eventId, event));
    }

    /* 설명. 이벤트 게시글 삭제*/
    @PatchMapping("/delete/{eventId}")
    @Operation(summary = "이벤트 게시글 삭제", description = "이벤트 게시글을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<EventBoard> deleteEvent(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int eventId) {
        return ResponseEntity.ok(eventBoardService.deleteEvent(eventId));
    }

    /* 설명. 이벤트 게시글 전체 조회 */
    @GetMapping("/list")
    @Operation(summary = "전체 이벤트 게시글 조회", description = "모든 이벤트 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    public List<EventBoard> findEventList() {
        List<EventBoard> eventList = eventBoardService.findEventList();

        return eventList;
    }

    /* 설명. 이벤트 게시글 단일 조회 */
    @GetMapping("{eventId}")
    @Operation(summary = "단일 이벤트 게시글 조회", description = "단일 이벤트 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public EventBoardDTO findEventById(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int eventId) {
        EventBoardDTO event = eventBoardService.findEventById(eventId);

        return event;
    }

    /* 설명. 퀴즈 답안 제출 */
    @PostMapping("/quiz/submit")
    @Operation(summary = "퀴즈 답안 제출", description = "퀴즈의 답안을 제출합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<QuizSolver> submitQuizAnswer(
            @Parameter(required = true, description = "퀴즈 응시자 정보")
            @RequestBody QuizSolverDTO quizSolverDTO) {
        quizService.submitAnswer(quizSolverDTO);
        return ResponseEntity.ok().build();
    }

    /* 설명. 투표 제출 */
    @PostMapping("/vote/submit")
    public ResponseEntity<?> submitVote(@RequestBody ReadyVoterDTO voterDTO) {
        voteService.submitVote(voterDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
