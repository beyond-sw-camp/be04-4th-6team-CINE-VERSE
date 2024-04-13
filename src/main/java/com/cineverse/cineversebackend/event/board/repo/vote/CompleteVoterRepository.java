package com.cineverse.cineversebackend.event.board.repo.vote;

import com.cineverse.cineversebackend.event.board.entity.vote.CompleteVoter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompleteVoterRepository extends JpaRepository<CompleteVoter, Integer> {
}
