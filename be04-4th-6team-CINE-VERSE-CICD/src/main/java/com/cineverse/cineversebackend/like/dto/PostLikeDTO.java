package com.cineverse.cineversebackend.like.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PostLikeDTO {
    private int like_id;
    private int member_id;
    private int event_id;
    private int review_id;
    private int free_id;
    private int info_id;
}
