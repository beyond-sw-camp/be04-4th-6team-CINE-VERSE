package com.cineverse.cineversebackend.info.comment.dto;

import com.cineverse.cineversebackend.user.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InfoCommentDTO {
    private int infoCommentId;
    private String commentContent;
    private String commentDate;
    private String commentDeleteDate;
    private Member member;
    private int infoId;
}
