package com.cineverse.cineversebackend.info.board.dto;

import com.cineverse.cineversebackend.image.entity.Image;
import com.cineverse.cineversebackend.info.board.entity.InfoCategory;
import com.cineverse.cineversebackend.user.member.entity.Member;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class InfoBoardDTO {
    private int infoId;
    private String infoTitle;
    private String infoContent;
    private int infoViewCount;
    private String infoDate;
    private String infoDeleteDate;
    private Member member;
    private InfoCategory infoCategory;
    private List<Image> images;
}

