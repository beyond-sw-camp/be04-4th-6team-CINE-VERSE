package com.cineverse.cineversebackend.free.board.service;

import com.cineverse.cineversebackend.free.board.dto.FreeBoardDTO;
import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FreeBoardService {
    void registFree(FreeBoardDTO newBoard, MultipartFile[] images);

    FreeBoard modifyFreeDeleteDate(int freeId);
    List<FreeBoard> findFreeList();

    FreeBoardDTO findFreeById(int freeId);

    void modifyFree(int freeId, FreeBoardDTO freeBoard, MultipartFile[] images);
}
