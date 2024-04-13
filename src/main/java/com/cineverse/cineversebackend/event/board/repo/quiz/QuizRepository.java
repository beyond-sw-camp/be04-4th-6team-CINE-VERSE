package com.cineverse.cineversebackend.event.board.repo.quiz;

import com.cineverse.cineversebackend.event.board.entity.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    Quiz findByEventBoard_EventBoardId(int eventId);
}
