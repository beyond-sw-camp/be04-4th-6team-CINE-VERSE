package com.cineverse.cineversebackend.user.report.repo;

import com.cineverse.cineversebackend.user.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}
