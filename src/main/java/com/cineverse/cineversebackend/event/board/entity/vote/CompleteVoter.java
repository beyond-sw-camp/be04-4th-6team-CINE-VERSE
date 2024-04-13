package com.cineverse.cineversebackend.event.board.entity.vote;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_complete_voter")
public class CompleteVoter {
    @Id
    @Column(name = "complete_voter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int completeVoterId;

//    @Column(name = "vote_content_id")
//    private int voteContentId;

    @ManyToOne
    @JoinColumn(name = "vote_content_id")
    private VoteContent voteContent;

//    @Column(name = "ready_voter_id")
//    private int readyVoterId;

    @ManyToOne
    @JoinColumn(name = "ready_voter_id")
    private ReadyVoter readyVoter;
}
