package com.cineverse.cineversebackend.event.board.repo.quiz;

import com.cineverse.cineversebackend.event.board.entity.quiz.QuizSolver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizSolverRepository extends JpaRepository<QuizSolver, Integer> {
    List<QuizSolver> findByQuiz_QuizId(int quizId);
    Optional<QuizSolver> findByMemberIdAndQuiz_QuizId(int memberId, int quizId);
}
