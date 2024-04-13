package com.cineverse.cineversebackend.user.report.service;

import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import com.cineverse.cineversebackend.free.board.repo.FreeBoardRepository;
import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
import com.cineverse.cineversebackend.info.board.repo.InfoBoardRepository;
import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
import com.cineverse.cineversebackend.review.board.repo.ReviewBoardRepository;
import com.cineverse.cineversebackend.user.member.entity.Member;
import com.cineverse.cineversebackend.user.member.repo.MemberRepository;
import com.cineverse.cineversebackend.user.report.dto.ReportDTO;
import com.cineverse.cineversebackend.user.report.entity.Report;
import com.cineverse.cineversebackend.user.report.repo.ReportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService{

    private final ModelMapper modelMapper;
    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;
    private final ReviewBoardRepository reviewBoardRepository;
    private final FreeBoardRepository freeBoardRepository;
    private final InfoBoardRepository infoBoardRepository;

    @Autowired
    public ReportServiceImpl(ModelMapper modelMapper,
                             ReportRepository reportRepository,
                             MemberRepository memberRepository,
                             ReviewBoardRepository reviewBoardRepository,
                             FreeBoardRepository freeBoardRepository,
                             InfoBoardRepository infoBoardRepository) {
        this.modelMapper = modelMapper;
        this.reportRepository = reportRepository;
        this.memberRepository = memberRepository;
        this.reviewBoardRepository = reviewBoardRepository;
        this.freeBoardRepository = freeBoardRepository;
        this.infoBoardRepository = infoBoardRepository;
    }

    @Override
    public Report registReport(Report report) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = format.format(date);

        report.setReportDate(newDate);
        report.setReportStatus("N");

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        reportRepository.save(report);
        return report;
    }

    /* 설명. 신고 반려 */
    @Override
    public Report processReportDeny(int boardReportId) {
        Optional<Report> optionalReport = reportRepository.findById(boardReportId);
        if (!optionalReport.isPresent()) {
            throw new EntityNotFoundException("신고 내역이 존재하지 않습니다.");
        }
        Report report = optionalReport.get();
        report.setReportStatus("Y");

        return reportRepository.save(report);
    }

    /* 설명. 신고 승인 */
    @Override
    public Report processReportAccept(int boardReportId) {
        Optional<Report> optionalReport = reportRepository.findById(boardReportId);
        if (!optionalReport.isPresent()) {
            throw new EntityNotFoundException("신고 내역이 존재하지 않습니다.");
        }
        Report report = optionalReport.get();
        report.setReportStatus("Y");

        reportRepository.save(report);                  // 신고

        addReportCount(report.getReportedMember().getMemberId());     // 회원의 신고 횟수를 누적시키는 메소드 호출


        return reportRepository.save(report);
    }

    private void addReportCount(int reportedMember) {
        Optional<Member> optionalMember = memberRepository.findById(reportedMember);
        if(!optionalMember.isPresent()) {
            throw new EntityNotFoundException("회원이 존재하지 않습니다.");
        }
        Member member = optionalMember.get();
        member.setReportNum(member.getReportNum() + 1);
        memberRepository.save(member);
    }

    /* 신고 단일 조회 */
    @Override
    public ReportDTO findBoardReportById(int boardReportId) {
        Report report = reportRepository.findById(boardReportId)
                .orElseThrow(() -> new NoSuchElementException("신고글을 찾지 못했습니다."));

        if (report.getReviewBoard() != null) {
            Optional<ReviewBoard> reviewBoard = reviewBoardRepository.findById(report.getReviewBoard().getReviewId());
            report.setReviewBoard(reviewBoard.get());
        }
        if (report.getFreeBoard() != null) {
            Optional<FreeBoard> freeBoard = freeBoardRepository.findById(report.getFreeBoard().getFreeId());
            report.setFreeBoard(freeBoard.get());
        }
        if (report.getInfoBoard() != null) {
            Optional<InfoBoard> infoBoard = infoBoardRepository.findById(report.getInfoBoard().getInfoId());
            report.setInfoBoard(infoBoard.get());
        }

        return modelMapper.map(report, ReportDTO.class);
    }

    /* 설명. 신고 전체조회 */
    @Override
    public List<Report> findBoardReportList() {
        List<Report> reportList = reportRepository.findAll(Sort.by("boardReportId"));

        return reportList.stream().map(report -> modelMapper.map(report, Report.class)).collect(Collectors.toList());
    }
}
