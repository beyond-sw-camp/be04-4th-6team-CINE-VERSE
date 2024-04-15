package com.cineverse.cineversebackend.event.board.dto.quiz;

import com.cineverse.cineversebackend.event.board.entity.EventBoard;
import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class QuizDTO {
    private int quizId;
    private String quizAnswer;
    private String quizQuestion;
    private String quizHint;
    private EventBoard eventBoard;
    private double correctRate;
//    private List<QuizSolver> quizSolvers = new ArrayList<>();
}
