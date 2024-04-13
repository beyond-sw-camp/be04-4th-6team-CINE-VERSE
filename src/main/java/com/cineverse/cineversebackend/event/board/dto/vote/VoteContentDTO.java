package com.cineverse.cineversebackend.event.board.dto.vote;

import com.cineverse.cineversebackend.event.board.entity.vote.Vote;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VoteContentDTO {
    private int voteContentId;
    private String voteCandidate;
    private int voteSequence;
//    private Vote vote;
}
