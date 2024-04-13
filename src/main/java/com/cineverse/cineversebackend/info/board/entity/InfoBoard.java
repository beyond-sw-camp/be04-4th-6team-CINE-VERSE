package com.cineverse.cineversebackend.info.board.entity;

import com.cineverse.cineversebackend.image.entity.Image;
import com.cineverse.cineversebackend.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_info_board")
public class InfoBoard {
    @Id
    @Column(name = "info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int infoId;

    @Column(name = "info_title")
    private String infoTitle;

    @Column(name = "info_content")
    private String infoContent;

    @Column(name = "info_view_count")
    private int infoViewCount;

    @Column(name = "info_date")
    private String infoDate;

    @Column(name = "info_delete_date")
    private String infoDeleteDate;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @JoinColumn(name = "info_category_id")
    @ManyToOne
    private InfoCategory infoCategory;

    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();
}
