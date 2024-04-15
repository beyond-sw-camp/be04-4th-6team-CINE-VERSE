package com.cineverse.cineversebackend.event.board.dto;

import com.cineverse.cineversebackend.event.board.dto.quiz.QuizDTO;
import com.cineverse.cineversebackend.event.board.dto.vote.VoteDTO;
import com.cineverse.cineversebackend.event.board.entity.quiz.Quiz;
import com.cineverse.cineversebackend.event.board.entity.vote.Vote;
import com.cineverse.cineversebackend.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class EventBoardDTO {
    private int eventBoardId;
    private String eventTitle;
    private String eventContent;
    private int eventViewCount;
    private String eventDate;
    private String eventDeleteDate;
    private Member member;
    private QuizDTO quiz;
    private VoteDTO vote;
}
