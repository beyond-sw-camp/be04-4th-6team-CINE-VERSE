package com.cineverse.cineversebackend.event.board.repo.vote;

import com.cineverse.cineversebackend.event.board.entity.vote.VoteContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteContentRepository extends JpaRepository<VoteContent, Integer> {
}
