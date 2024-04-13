package com.cineverse.cineversebackend.free.comment.dto;

import com.cineverse.cineversebackend.user.member.entity.Member;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FreeCommentDTO {
    private int freeCommentId;
    private String commentContent;
    private String commentDate;
    private String commentDeleteDate;
    private Member member;
    private int freeId;
}
