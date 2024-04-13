package com.cineverse.cineversebackend.user.report.controller;

import com.cineverse.cineversebackend.user.report.dto.ReportDTO;
import com.cineverse.cineversebackend.user.report.entity.Report;
import com.cineverse.cineversebackend.user.report.service.ReportService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private ReportService reportService;
    private ModelMapper modelMapper;

    @Autowired
    public ReportController(ReportService reportService, ModelMapper modelMapper) {
        this.reportService = reportService;
        this.modelMapper = modelMapper;
    }

    /* 설명. 게시글 신고 */
    @PostMapping("/regist")
    private ResponseEntity<Report> registReport(@RequestBody ReportDTO reportDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Report report = modelMapper.map(reportDTO, Report.class);
        reportService.registReport(report);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 설명. 게시글 신고 접수(승인X) */
    @GetMapping("/processDeny/{boardReportId}")
    public ResponseEntity<Report> processReportDeny (@PathVariable int boardReportId) {
        return ResponseEntity.ok(reportService.processReportDeny(boardReportId));
    }

    /* 설명. 게시글 신고 접수(승인O) */
    @GetMapping("/processAccept/{boardReportId}")
    public ResponseEntity<Report> processReportAccept (@PathVariable int boardReportId) {
        return ResponseEntity.ok(reportService.processReportAccept(boardReportId));
    }

    /* 설명. 게시글 신고 단일조회 */
    @GetMapping("/{boardReportId}")
    public ReportDTO findBoardReportByReportId(@PathVariable int boardReportId) {
        ReportDTO report = reportService.findBoardReportById(boardReportId);

        return report;
    }

    /* 설명. 게시글 신고 전체조회 */
    @GetMapping("/list")
    public List<Report> findBoardReportList() {
        List<Report> reportList = reportService.findBoardReportList();

        return reportService.findBoardReportList();
    }
}
