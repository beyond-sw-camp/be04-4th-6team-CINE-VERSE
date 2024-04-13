package com.cineverse.cineversebackend.badge.repo;

import com.cineverse.cineversebackend.badge.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge, Integer> {
}
