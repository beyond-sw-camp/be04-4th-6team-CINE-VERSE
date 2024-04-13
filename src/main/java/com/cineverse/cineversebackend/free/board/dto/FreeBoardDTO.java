package com.cineverse.cineversebackend.free.board.dto;

import com.cineverse.cineversebackend.image.entity.Image;
import com.cineverse.cineversebackend.user.member.entity.Member;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FreeBoardDTO {
    private int freeId;
    private String freeTitle;
    private String freeContent;
    private int freeViewCount;
    private String freeDate;
    private String freeDeleteDate;
    private Member member;
    private List<Image> images;
}
