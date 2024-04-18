package com.cineverse.cineversebackend.event.board.entity.vote;

import com.cineverse.cineversebackend.event.board.entity.EventBoard;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_vote")
public class Vote {
    @Id
    @Column(name = "vote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteId;

    @Column(name = "vote_end_time")
    private String voteEndTime;

    @Column(name = "vote_title")
    private String voteTitle;

    @ManyToOne
    @JoinColumn(name = "event_board_id")
    private EventBoard eventBoard;

//    @OneToOne(mappedBy = "vote")
//    @JoinColumn(name = "event_board_id")
//    private EventBoard eventBoard;

//    @ManyToOne
//    @JoinColumn(name = "vote_content")
//    private VoteContent voteContent;

//    @OneToMany(mappedBy = "vote")
//    @JoinColumn(name = "vote_content")
//    private List<VoteContent> voteContent;

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<VoteContent> voteContentList;
}
