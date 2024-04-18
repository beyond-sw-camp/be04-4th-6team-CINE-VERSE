package com.cineverse.cineversebackend.free.board.entity;

import com.cineverse.cineversebackend.image.entity.Image;
import com.cineverse.cineversebackend.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_free_board")
@Builder
public class FreeBoard {
    @Id
    @Column(name = "free_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int freeId;

    @Column(name = "free_title")
    private String freeTitle;

    @Column(name = "free_content")
    private String freeContent;

    @Column(name = "free_view_count")
    private int freeViewCount;

    @Column(name = "free_date")
    private String freeDate;

    @Column(name = "free_delete_date")
    private String freeDeleteDate;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "free", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

}
