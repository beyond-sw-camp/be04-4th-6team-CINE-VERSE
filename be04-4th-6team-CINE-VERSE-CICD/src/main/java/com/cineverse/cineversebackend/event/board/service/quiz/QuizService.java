package com.cineverse.cineversebackend.event.board.service.quiz;

import com.cineverse.cineversebackend.event.board.dto.EventBoardDTO;
import com.cineverse.cineversebackend.event.board.dto.quiz.QuizDTO;
import com.cineverse.cineversebackend.event.board.dto.quiz.QuizSolverDTO;
import com.cineverse.cineversebackend.event.board.entity.EventBoard;

import java.util.List;

public interface QuizService {
    void submitAnswer(QuizSolverDTO quizSolverDTO);

    List<QuizDTO> calculateCorrectRate();
}

