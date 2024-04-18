package com.cineverse.cineversebackend.event.board.entity.vote;

import com.cineverse.cineversebackend.event.board.dto.vote.VoteDTO;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "vote_content")
public class VoteContent {
    @Id
    @Column(name = "vote_content_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteContentId;

    @Column(name = "vote_candidate")
    private String voteCandidate;

    @Column(name = "vote_sequence")
    private int voteSequence;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;
}
