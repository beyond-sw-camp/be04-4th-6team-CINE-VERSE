package com.cineverse.cineversebackend.event.board.repo.vote;

import com.cineverse.cineversebackend.event.board.entity.vote.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Vote findByEventBoard_EventBoardId(int eventId);
}
