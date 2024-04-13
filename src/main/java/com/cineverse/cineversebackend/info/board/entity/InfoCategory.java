package com.cineverse.cineversebackend.info.board.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_info_category")
public class InfoCategory {
    @Id
    @Column(name = "info_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int infoCategoryId;

    @Column(name = "info_category")
    private String infoCategory;
}
