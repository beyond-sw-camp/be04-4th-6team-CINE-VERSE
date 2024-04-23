package com.cineverse.cineversebackend.event.board.service.vote;

import com.cineverse.cineversebackend.event.board.dto.vote.ReadyVoterDTO;
import com.cineverse.cineversebackend.event.board.dto.vote.VoteContentDTO;
import com.cineverse.cineversebackend.event.board.dto.vote.VoteDTO;
//import com.cineverse.cineversebackend.event.board.dto.vote.VoterDTO;

import java.util.List;

public interface VoteService {
    void submitVote(ReadyVoterDTO voterDTO);
}
