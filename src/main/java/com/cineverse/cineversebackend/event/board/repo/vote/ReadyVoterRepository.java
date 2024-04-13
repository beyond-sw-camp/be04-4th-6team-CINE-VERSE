package com.cineverse.cineversebackend.event.board.repo.vote;

import com.cineverse.cineversebackend.event.board.entity.vote.ReadyVoter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadyVoterRepository extends JpaRepository<ReadyVoter, Integer> {
}
