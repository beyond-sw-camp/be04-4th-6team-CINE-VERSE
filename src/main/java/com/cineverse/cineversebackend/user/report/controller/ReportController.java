package com.cineverse.cineversebackend.user.report.controller;

import com.cineverse.cineversebackend.user.report.dto.ReportDTO;
import com.cineverse.cineversebackend.user.report.entity.Report;
import com.cineverse.cineversebackend.user.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "게시글 신고", description = "게시글을 신고합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    private ResponseEntity<Report> registReport(
            @Parameter(required = true, description = "게시글 신고 요청")
            @RequestBody ReportDTO reportDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Report report = modelMapper.map(reportDTO, Report.class);
        reportService.registReport(report);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 설명. 게시글 신고 접수(승인X) */
    @GetMapping("/processDeny/{boardReportId}")
    @Operation(summary = "게시글 신고 기각", description = "게시글 신고를 기각합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<Report> processReportDeny (
            @Parameter(required = true, description = "게시글 신고 고유번호")
            @PathVariable int boardReportId) {
        return ResponseEntity.ok(reportService.processReportDeny(boardReportId));
    }

    /* 설명. 게시글 신고 접수(승인O) */
    @GetMapping("/processAccept/{boardReportId}")
    @Operation(summary = "게시글 신고 승인", description = "게시글을 신고를 승인합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<Report> processReportAccept (
            @Parameter(required = true, description = "게시글 신고 고유번호")
            @PathVariable int boardReportId) {
        return ResponseEntity.ok(reportService.processReportAccept(boardReportId));
    }

    /* 설명. 게시글 신고 단일조회 */
    @GetMapping("/{boardReportId}")
    @Operation(summary = "게시글 신고 단일 조회", description = "게시글 신고 단일 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ReportDTO findBoardReportByReportId(
            @Parameter(required = true, description = "게시글 신고 고유번호")
            @PathVariable int boardReportId) {
        ReportDTO report = reportService.findBoardReportById(boardReportId);

        return report;
    }

    /* 설명. 게시글 신고 전체조회 */
    @GetMapping("/list")
    @Operation(summary = "전체 게시글 신고조회", description = "전체 게시글 신고를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    public List<Report> findBoardReportList() {
        List<Report> reportList = reportService.findBoardReportList();

        return reportService.findBoardReportList();
    }
}
