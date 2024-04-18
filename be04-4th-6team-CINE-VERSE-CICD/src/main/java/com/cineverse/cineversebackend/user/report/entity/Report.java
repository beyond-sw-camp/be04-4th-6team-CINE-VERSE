package com.cineverse.cineversebackend.user.report.entity;

import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
import com.cineverse.cineversebackend.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "tbl_board_report")
public class Report {

    @Id
    @Column(name = "board_report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardReportId;

    @Column(name = "report_content")
    private String reportContent;

    @Column(name = "report_date")
    private String reportDate;

    @Column(name = "report_status")
    private String reportStatus;

    @Column(name = "report_category_id")
    private int reportCategoryId;

    @JoinColumn(name = "report_member")
    @ManyToOne
    private Member reportMember;

    @JoinColumn(name = "reported_member")
    @ManyToOne
    private Member reportedMember;

    @Column(name = "event_id")
    private Integer eventId;

    @JoinColumn(name = "review_id")
    @ManyToOne
    private ReviewBoard reviewBoard;

    @JoinColumn(name = "free_id")
    @ManyToOne
    private FreeBoard freeBoard;

    @JoinColumn(name = "info_id")
    @ManyToOne
    private InfoBoard infoBoard;
}
