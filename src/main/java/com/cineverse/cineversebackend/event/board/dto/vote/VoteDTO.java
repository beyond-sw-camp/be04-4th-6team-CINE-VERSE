package com.cineverse.cineversebackend.event.board.dto.vote;

import com.cineverse.cineversebackend.event.board.entity.vote.VoteContent;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class VoteDTO {
    private int voteId;
    private String voteEndTime;
    private String voteTitle;
    private List<VoteContentDTO> voteContents;
}
