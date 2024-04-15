//package com.cineverse.cineversebackend.user;
//
//import com.cineverse.cineversebackend.free.board.dto.FreeBoardDTO;
//import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
//import com.cineverse.cineversebackend.free.board.service.FreeBoardService;
//import com.cineverse.cineversebackend.user.member.entity.Member;
//import com.cineverse.cineversebackend.user.member.service.MemberService;
//import com.cineverse.cineversebackend.user.report.dto.ReportDTO;
//import com.cineverse.cineversebackend.user.report.entity.Report;
//import com.cineverse.cineversebackend.user.report.repo.ReportRepository;
//import com.cineverse.cineversebackend.user.report.service.ReportService;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DataIntegrityViolationException;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//@SpringBootTest
//public class ReportTests {
//
//    private final ReportService reportService;
//    private final ReportRepository reportRepository;
//    private final MemberService memberService;
//    private final FreeBoardService freeBoardService;
//    private final ModelMapper mapper;
//
//    @Autowired
//    public ReportTests(ReportService reportService,
//                       ReportRepository reportRepository,
//                       MemberService memberService,
//                       FreeBoardService freeBoardService,
//                       ModelMapper mapper) {
//        this.reportService = reportService;
//        this.reportRepository = reportRepository;
//        this.memberService = memberService;
//        this.freeBoardService = freeBoardService;
//        this.mapper = mapper;
//    }
//
//    @BeforeEach
//    public void setup() {
//        Member reportMember = memberService.findMemberById(1);
//        Member reportedMember = memberService.findMemberById(2);
//        FreeBoardDTO freeBoardDTO = freeBoardService.findFreeById(1);
//
//        FreeBoard freeBoard = mapper.map(freeBoardDTO, FreeBoard.class);
//
//        Report report = Report.builder()
//                .reportContent("테스트")
//                .reportCategoryId(1)
//                .reportMember(reportMember)
//                .reportedMember(reportedMember)
//                .freeBoard(freeBoard)
//                .reportStatus("N")
//                .reportDate("2024.04.13")
//                .build();
//
//        reportRepository.save(report);
//    }
//
//    @Test
//    @DisplayName("신고 단일 조회 테스트")
//    @Transactional
//    public void findReportByIdTest() {
//        Member reportMember = memberService.findMemberById(10);
//        Member reportedMember = memberService.findMemberById(2);
//        FreeBoardDTO freeBoardDTO = freeBoardService.findFreeById(1);
//
//        FreeBoard freeBoard = mapper.map(freeBoardDTO, FreeBoard.class);
//
//        Report report = Report.builder()
//                .reportContent("테스트")
//                .reportCategoryId(1)
//                .reportMember(reportMember)
//                .reportedMember(reportedMember)
//                .freeBoard(freeBoard)
//                .build();
//
//        Report registReport = reportService.registReport(report);
//
//        ReportDTO reportDTO = reportService.findBoardReportById(registReport.getBoardReportId());
//        Report selectReport = mapper.map(reportDTO, Report.class);
//
//        assertThat(selectReport.getReportContent()).isEqualTo(report.getReportContent());
//        assertThat(selectReport.getReportCategoryId()).isEqualTo(report.getReportCategoryId());
//        assertThat(selectReport.getReportMember()).isEqualTo(report.getReportMember());
//        assertThat(selectReport.getReportedMember()).isEqualTo(report.getReportedMember());
//        assertThat(selectReport.getFreeBoard()).isEqualTo(report.getFreeBoard());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("신고 전체조회 테스트")
//    public void selectAllReportsTest() {
//        List<Report> reportList = reportService.findBoardReportList();
//
//        boolean test = false;
//
//        if (!reportList.isEmpty()) {
//            test = true;
//        } else {
//            test = false;
//        }
//
//        assertThat(test).isEqualTo(true);
//    }
//
//
//    @Test
//    @DisplayName("신고 게시글 생성 테스트")
//    @Transactional
//    public void successRegistReport() {
//        Member reportMember = memberService.findMemberById(3);
//        Member reportedMember = memberService.findMemberById(4);
//        FreeBoardDTO freeBoardDTO = freeBoardService.findFreeById(1);
//
//        FreeBoard freeBoard = mapper.map(freeBoardDTO, FreeBoard.class);
//
//        Report report = Report.builder()
//                .reportContent("테스트")
//                .reportCategoryId(1)
//                .reportMember(reportMember)
//                .reportedMember(reportedMember)
//                .freeBoard(freeBoard)
//                .build();
//
//        Report registReport = reportService.registReport(report);
//
//        ReportDTO findReport = reportService.findBoardReportById(registReport.getBoardReportId());
//
//        assertThat(registReport.getReportContent()).isEqualTo(findReport.getReportContent());
//        assertThat(registReport.getReportCategoryId()).isEqualTo(findReport.getReportCategoryId());
//        assertThat(registReport.getReportMember()).isEqualTo(findReport.getReportMember());
//        assertThat(registReport.getReportMember()).isEqualTo(findReport.getReportMember());
//        assertThat(registReport.getFreeBoard()).isEqualTo(findReport.getFreeBoard());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("중복 신고 테스트")
//    public void registDuplicateMember() {
//        Member reportMember = memberService.findMemberById(1);
//        Member reportedMember = memberService.findMemberById(2);
//        FreeBoardDTO freeBoardDTO = freeBoardService.findFreeById(1);
//
//        FreeBoard freeBoard = mapper.map(freeBoardDTO, FreeBoard.class);
//
//        Report report = Report.builder()
//                .reportContent("테스트")
//                .reportCategoryId(1)
//                .reportMember(reportMember)
//                .reportedMember(reportedMember)
//                .freeBoard(freeBoard)
//                .build();
//
//        assertThatThrownBy(() -> reportService.registReport(report))
//                .isInstanceOf(DataIntegrityViolationException.class)
//                .hasMessageContaining("Duplicate entry");
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("신고 반려 테스트")
//    public void reportDenyTest() {
//        ReportDTO reportDTO = reportService.findBoardReportById(2);
//        Report beforeProcess = mapper.map(reportDTO, Report.class);
//
//        Report afterProcess = reportService.processReportDeny(2);
//
//        assertThat(beforeProcess.getReportStatus()).isEqualTo("N");
//        assertThat(afterProcess.getReportStatus()).isEqualTo("Y");
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("신고 승인 테스트")
//    public void reportAcceptTest() {
//        ReportDTO reportDTO = reportService.findBoardReportById(2);
//        Report beforeProcess = mapper.map(reportDTO, Report.class);
//        int beforeReportNum = beforeProcess.getReportedMember().getReportNum();
//
//        Report afterProcess = reportService.processReportAccept(2);
//
//        int afterReportNum = afterProcess.getReportedMember().getReportNum();
//
//        assertThat(beforeProcess.getReportStatus()).isEqualTo("N");
//        assertThat(afterProcess.getReportStatus()).isEqualTo("Y");
//        assertThat(beforeReportNum).isNotEqualTo(afterReportNum);
//        assertThat(afterReportNum).isEqualTo(beforeReportNum + 1);
//    }
//}
