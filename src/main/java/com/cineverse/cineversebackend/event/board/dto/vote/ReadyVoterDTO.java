package com.cineverse.cineversebackend.event.board.dto.vote;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReadyVoterDTO {
    private int readyVoterId;
    private int memberId;
}
