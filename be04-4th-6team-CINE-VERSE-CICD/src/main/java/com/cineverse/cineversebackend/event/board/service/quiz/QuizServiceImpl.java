package com.cineverse.cineversebackend.event.board.service.quiz;

import com.cineverse.cineversebackend.event.board.dto.EventBoardDTO;
import com.cineverse.cineversebackend.event.board.dto.quiz.QuizDTO;
import com.cineverse.cineversebackend.event.board.dto.quiz.QuizSolverDTO;
import com.cineverse.cineversebackend.event.board.entity.EventBoard;
import com.cineverse.cineversebackend.event.board.entity.quiz.Quiz;
import com.cineverse.cineversebackend.event.board.entity.quiz.QuizSolver;
import com.cineverse.cineversebackend.event.board.repo.EventBoardRepository;
import com.cineverse.cineversebackend.event.board.repo.quiz.QuizRepository;
import com.cineverse.cineversebackend.event.board.repo.quiz.QuizSolverRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private final EventBoardRepository eventBoardRepository;
    private final QuizRepository quizRepository;
    private final QuizSolverRepository quizSolverRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public QuizServiceImpl(EventBoardRepository eventBoardRepository,
                           QuizRepository quizRepository,
                           QuizSolverRepository quizSolverRepository,
                           ModelMapper modelMapper) {
        this.eventBoardRepository = eventBoardRepository;
        this.quizRepository = quizRepository;
        this.quizSolverRepository = quizSolverRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void submitAnswer(QuizSolverDTO quizSolverDTO) {
        Optional<QuizSolver> existingSubmission = quizSolverRepository
                .findByMemberIdAndQuiz_QuizId(quizSolverDTO.getMemberId(), quizSolverDTO.getQuizId());
        if (existingSubmission.isPresent()) {
            throw new IllegalStateException("이미 퀴즈를 제출하였습니다.");
        }

        Quiz quiz = quizRepository.findById(quizSolverDTO.getQuizId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 퀴즈입니다."));

        QuizSolver quizSolver = new QuizSolver();
//        quizSolver.setMemberId(quizSolver.getMemberId());
        quizSolver.setMemberId(quizSolverDTO.getMemberId());
        quizSolver.setQuizCorrect(quizSolverDTO.getQuizCorrect());
        quizSolver.setQuiz(quiz);
        quizSolverRepository.save(quizSolver);
    }

    @Override
    public List<QuizDTO> calculateCorrectRate() {
        List<Quiz> quizList = quizRepository.findAll();
        List<QuizDTO> quizDTOList = new ArrayList<>();

        for (Quiz quiz : quizList) {
            List<QuizSolver> quizSolvers = quizSolverRepository.findByQuiz_QuizId(quiz.getQuizId());
            long totalAnswers = quizSolvers.size();
            long correctAnswers = quizSolvers.stream()
                    .filter(qs -> "Y".equals(qs.getQuizCorrect()))
                    .count();
            double correctRate
                    = (totalAnswers > 0) ? Math.round(((double) correctAnswers / totalAnswers) * 1000) / 10.0 : 0;

            QuizDTO quizDTO = modelMapper.map(quiz, QuizDTO.class);
            quizDTO.setCorrectRate(correctRate);
            quizDTOList.add(quizDTO);
        }
        return quizDTOList;
    }
}

