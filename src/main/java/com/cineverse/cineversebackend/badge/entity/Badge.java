package com.cineverse.cineversebackend.badge.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tbl_badge")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private int badgeId;

    @Column(name = "badge_location")
    private String badgeLocation;

    @Column(name = "badge_name")
    private String badgeName;
}
