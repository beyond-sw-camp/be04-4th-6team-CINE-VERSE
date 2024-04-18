package com.cineverse.cineversebackend.user.report.dto;


import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
import com.cineverse.cineversebackend.user.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReportDTO {
    private int boardReportId;
    private String reportContent;
    private String reportDate;
    private String reportStatus;
    private int reportCategoryId;
    private Member reportMember;
    private Member reportedMember;
    private Integer eventId;
    private ReviewBoard reviewBoard;
    private FreeBoard freeBoard;
    private InfoBoard infoBoard;
}
