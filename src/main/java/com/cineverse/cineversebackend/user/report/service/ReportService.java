package com.cineverse.cineversebackend.user.report.service;

import com.cineverse.cineversebackend.user.report.dto.ReportDTO;
import com.cineverse.cineversebackend.user.report.entity.Report;

import java.util.List;

public interface ReportService {
    Report registReport(Report report);

    Report processReportDeny(int boardReportId);

    Report processReportAccept(int boardReportId);

    ReportDTO findBoardReportById(int boardReportId);

    List<Report> findBoardReportList();
}
