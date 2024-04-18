package com.cineverse.cineversebackend.free.comment.entity;

import com.cineverse.cineversebackend.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="tbl_free_comment")
@Builder
public class FreeComment {

    @Id
    @Column(name = "free_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int freeCommentId;

    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "comment_date")
    private String commentDate;

    @Column(name = "comment_delete_date")
    private String commentDeleteDate;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member member;

    @Column(name = "free_id")
    private int freeId;
}
