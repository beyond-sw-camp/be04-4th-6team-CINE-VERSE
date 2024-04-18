package com.cineverse.cineversebackend.event.board.entity.vote;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_ready_voter")
public class ReadyVoter {
    @Id
    @Column(name = "ready_voter_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int readyVoterId;

    @Column(name = "member_id")
    private int memberId;
}
