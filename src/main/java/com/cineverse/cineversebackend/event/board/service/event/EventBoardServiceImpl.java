package com.cineverse.cineversebackend.event.board.service.event;

import com.cineverse.cineversebackend.event.board.dto.EventBoardDTO;
import com.cineverse.cineversebackend.event.board.dto.quiz.QuizDTO;
import com.cineverse.cineversebackend.event.board.dto.vote.VoteContentDTO;
import com.cineverse.cineversebackend.event.board.dto.vote.VoteDTO;
import com.cineverse.cineversebackend.event.board.entity.EventBoard;
import com.cineverse.cineversebackend.event.board.entity.quiz.Quiz;
import com.cineverse.cineversebackend.event.board.entity.vote.Vote;
import com.cineverse.cineversebackend.event.board.entity.vote.VoteContent;
import com.cineverse.cineversebackend.event.board.repo.EventBoardRepository;
import com.cineverse.cineversebackend.event.board.repo.quiz.QuizRepository;
import com.cineverse.cineversebackend.event.board.repo.vote.VoteContentRepository;
import com.cineverse.cineversebackend.event.board.repo.vote.VoteRepository;
import com.cineverse.cineversebackend.event.board.service.quiz.QuizService;
import com.cineverse.cineversebackend.point.service.PointService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventBoardServiceImpl implements EventBoardService {
    private final ModelMapper modelMapper;
    private final EventBoardRepository eventBoardRepository;
    private final QuizRepository quizRepository;
    private final QuizService quizService;
    private final VoteRepository voteRepository;
    private final VoteContentRepository voteContentRepository;
    private final PointService pointService;

    @Autowired
    public EventBoardServiceImpl(ModelMapper modelMapper,
                                 EventBoardRepository eventBoardRepository,
                                 QuizRepository quizRepository,
                                 QuizService quizService,
                                 VoteRepository voteRepository,
                                 VoteContentRepository voteContentRepository,
                                 PointService pointService) {
        this.modelMapper = modelMapper;
        this.eventBoardRepository = eventBoardRepository;
        this.quizRepository = quizRepository;
        this.quizService = quizService;
        this.voteRepository = voteRepository;
        this.voteContentRepository = voteContentRepository;
        this.pointService = pointService;
    }

    @Override
    @Transactional
    public EventBoard registEvent(EventBoardDTO eventDTO) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);
        eventDTO.setEventDate(registDate);

        EventBoard newEvent = modelMapper.map(eventDTO, EventBoard.class);
        newEvent = eventBoardRepository.saveAndFlush(newEvent);

        if(newEvent.getEventBoardId() == 0) {
            throw new IllegalStateException("EventBoard ID has not been generated.");
        }

        if(eventDTO.getQuiz() != null) {
            Quiz quiz = modelMapper.map(eventDTO.getQuiz(), Quiz.class);
            quiz.setEventBoard(newEvent);
            quizRepository.save(quiz);
        }

        if (eventDTO.getVote() != null) {
            Vote vote = modelMapper.map(eventDTO.getVote(), Vote.class);
            vote.setEventBoard(newEvent);

            // 먼저 Vote 엔티티를 저장합니다.
            Vote savedVote = voteRepository.save(vote);

            // 이제 각 VoteContent 객체들에 Vote 참조를 설정합니다.
            if (eventDTO.getVote().getVoteContents() != null) {
                for (VoteContentDTO contentDTO : eventDTO.getVote().getVoteContents()) {
                    VoteContent voteContent = modelMapper.map(contentDTO, VoteContent.class);
                    voteContent.setVote(savedVote);
                    voteContentRepository.save(voteContent); // 각 VoteContent 엔티티 저장
                }
            }
        }
        pointService.addBoardPoint(newEvent.getMember().getMemberId());
        return newEvent;
    }

    @Override
    @Transactional
    public EventBoard modifyEvent(int eventId, EventBoardDTO event) {
        Optional<EventBoard> optionalEventBoard = eventBoardRepository.findById(eventId);
        if (optionalEventBoard.isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 게시글입니다.");
        }

        EventBoard eventBoard = optionalEventBoard.get();

        if (eventBoard.getEventTitle() != null) {
            eventBoard.setEventTitle(eventBoard.getEventTitle());
        }

        if (eventBoard.getEventContent() != null) {
            eventBoard.setEventContent(event.getEventContent());
        }

        return eventBoardRepository.save(eventBoard);
    }

    @Override
    public EventBoard deleteEvent(int eventId) {
        Optional<EventBoard> optionalEventBoard = eventBoardRepository.findById(eventId);
        if (optionalEventBoard.isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 게시글입니다.");
        }

        EventBoard eventBoard = optionalEventBoard.get();

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String deleteDate = format.format(date);
        eventBoard.setEventDeleteDate(deleteDate);

        return eventBoardRepository.save(eventBoard);
    }

    @Override
    public List<EventBoard> findEventList() {
        List<EventBoard> eventList = eventBoardRepository.findByEventDeleteDateIsNullOrderByEventBoardIdDesc();

        return eventList.stream().map(eventBoard -> modelMapper
                .map(eventBoard, EventBoard.class))
                .collect(Collectors.toList());
    }

    @Override
    public EventBoardDTO findEventById(int eventId) {
        EventBoard eventBoard = eventBoardRepository.findById(eventId)
                .orElseThrow(IllegalAccessError::new);

        eventBoard.setEventViewCount(eventBoard.getEventViewCount() + 1);
        eventBoardRepository.save(eventBoard);

        EventBoardDTO eventBoardDTO = modelMapper.map(eventBoard, EventBoardDTO.class);

        Quiz quiz = quizRepository.findByEventBoard_EventBoardId(eventId);
        if (quiz != null) {
            QuizDTO quizDTO = modelMapper.map(quiz, QuizDTO.class);

            List<QuizDTO> quizDTOList = quizService.calculateCorrectRate();

            quizDTOList.stream()
                    .filter(q -> q.getQuizId() == quiz.getQuizId())
                    .findFirst()
                    .ifPresent(q -> quizDTO.setCorrectRate(q.getCorrectRate()));

            eventBoardDTO.setQuiz(quizDTO);
        }

        Vote vote = voteRepository.findByEventBoard_EventBoardId(eventId);
        if (vote != null) {
            VoteDTO voteDTO = new VoteDTO();
            voteDTO.setVoteId(vote.getVoteId());
            voteDTO.setVoteEndTime(vote.getVoteEndTime());
            voteDTO.setVoteTitle(vote.getVoteTitle());

            List<VoteContentDTO> voteContentDTOs = vote.getVoteContentList().stream()
                    .map(content -> modelMapper.
                            map(content, VoteContentDTO.class))
                    .collect(Collectors.toList());
            voteDTO.setVoteContents(voteContentDTOs);
            eventBoardDTO.setVote(voteDTO);
        }

        return eventBoardDTO;
    }
}
