package com.cineverse.cineversebackend.event.board.entity.quiz;

import com.cineverse.cineversebackend.event.board.entity.EventBoard;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_quiz")
public class Quiz {
    @Id
    @Column(name = "quiz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quizId;

    @Column(name = "quiz_answer")
    private String quizAnswer;

    @Column(name = "quiz_question")
    private String quizQuestion;

    @Column(name = "quiz_hint")
    private String quizHint;

    @OneToMany(mappedBy = "quiz")
    private List<QuizSolver> quizSolvers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "event_board_id")
    private EventBoard eventBoard;
}
