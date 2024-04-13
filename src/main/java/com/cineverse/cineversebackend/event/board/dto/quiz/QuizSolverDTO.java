package com.cineverse.cineversebackend.event.board.dto.quiz;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuizSolverDTO {
    private int quizSolverId;
    private int memberId;
    private int quizId;
    private String quizCorrect;
}
