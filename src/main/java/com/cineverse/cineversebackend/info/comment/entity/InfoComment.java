package com.cineverse.cineversebackend.info.comment.entity;

import com.cineverse.cineversebackend.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_info_comment")
public class InfoComment {

    @Id
    @Column(name = "info_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int infoCommentId;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_date")
    private String commentDate;

    @Column(name = "comment_delete_date")
    private String commentDeleteDate;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @Column(name = "info_id")
    private int infoId;
}
