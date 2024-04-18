package com.cineverse.cineversebackend.event.board.service.vote;

import com.cineverse.cineversebackend.event.board.dto.vote.VoteContentDTO;
import com.cineverse.cineversebackend.event.board.dto.vote.VoteDTO;
//import com.cineverse.cineversebackend.event.board.dto.vote.VoterDTO;
import com.cineverse.cineversebackend.event.board.entity.EventBoard;
import com.cineverse.cineversebackend.event.board.entity.vote.Vote;
import com.cineverse.cineversebackend.event.board.entity.vote.VoteContent;
//import com.cineverse.cineversebackend.event.board.entity.vote.Voter;
import com.cineverse.cineversebackend.event.board.repo.EventBoardRepository;
import com.cineverse.cineversebackend.event.board.repo.vote.CompleteVoterRepository;
import com.cineverse.cineversebackend.event.board.repo.vote.ReadyVoterRepository;
import com.cineverse.cineversebackend.event.board.repo.vote.VoteContentRepository;
import com.cineverse.cineversebackend.event.board.repo.vote.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    private final EventBoardRepository eventBoardRepository;
    private final VoteRepository voteRepository;
    private final VoteContentRepository voteContentRepository;
    private final ReadyVoterRepository readyVoterRepository;
    private final CompleteVoterRepository completeVoterRepository;

    @Autowired
    public VoteServiceImpl(EventBoardRepository eventBoardRepository,
                           VoteRepository voteRepository,
                           VoteContentRepository voteContentRepository,
                           ReadyVoterRepository readyVoterRepository,
                           CompleteVoterRepository completeVoterRepository) {
        this.eventBoardRepository = eventBoardRepository;
        this.voteRepository = voteRepository;
        this.voteContentRepository = voteContentRepository;
        this.readyVoterRepository = readyVoterRepository;
        this.completeVoterRepository = completeVoterRepository;
    }

//    @Override
//    public void submitVote(VoterDTO voterDTO) {
//        Optional<Voter> existingVote = voterRepository
//                .findByVoteContentIdAndMemberId(voterDTO.getVoteContentId(), voterDTO.getMemberId());
//        if (existingVote.isPresent()) {
//            throw new IllegalStateException("이미 투표하였습니다.");
//        }
//
//        Voter voter = new Voter();
//        voter.setVoteContentId(voterDTO.getVoteContentId());
//        voter.setMemberId(voterDTO.getMemberId());
//        voterRepository.save(voter); // Voter 저장
//    }
}