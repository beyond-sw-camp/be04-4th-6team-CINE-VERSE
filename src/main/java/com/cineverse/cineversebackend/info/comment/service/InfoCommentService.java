package com.cineverse.cineversebackend.info.comment.service;

import com.cineverse.cineversebackend.info.comment.dto.InfoCommentDTO;
import com.cineverse.cineversebackend.info.comment.entity.InfoComment;

import java.util.List;

public interface InfoCommentService {
    void registInfoComment(InfoComment infoComment);

    InfoComment modifyInfoComment(int infoCommentId, InfoCommentDTO infoCommentDTO);

    InfoComment modifyInfoCommentDeleteDate(int infoCommentId, InfoCommentDTO infoCommentDTO);

    List<InfoComment> findInfoCommentList();
}
