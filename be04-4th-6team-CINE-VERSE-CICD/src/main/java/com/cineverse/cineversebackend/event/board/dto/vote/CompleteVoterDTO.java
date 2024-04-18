package com.cineverse.cineversebackend.event.board.dto.vote;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CompleteVoterDTO {
    private int completeVoterId;
    private int voteContentId;
    private int readyVoterId;
}
