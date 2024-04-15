package com.cineverse.cineversebackend.event.board.entity;

import com.cineverse.cineversebackend.event.board.entity.quiz.Quiz;
import com.cineverse.cineversebackend.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_event_board")
@Builder
public class EventBoard {
    @Id
    @Column(name = "event_board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventBoardId;

    @Column(name = "event_title")
    private String eventTitle;

    @Column(name = "event_content")
    private String eventContent;

    @Column(name = "event_view_count")
    private int eventViewCount;

    @Column(name = "event_date")
    private String eventDate;

    @Column(name = "event_delete_date")
    private String eventDeleteDate;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;
}
