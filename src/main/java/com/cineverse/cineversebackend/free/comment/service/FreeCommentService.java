package com.cineverse.cineversebackend.free.comment.service;

import com.cineverse.cineversebackend.free.comment.dto.FreeCommentDTO;
import com.cineverse.cineversebackend.free.comment.entity.FreeComment;

import java.util.List;

public interface FreeCommentService {
    void registFreeComment(FreeComment freeComment);
    FreeComment modifyFreeComment(int freeCommentId, FreeCommentDTO freeCommentDTO);
    FreeComment modifyFreeCommentDeleteDate(int freeCommentId, FreeCommentDTO freeCommentDTO);
    List<FreeComment> findFreeCommentList();
}
