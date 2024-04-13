package com.cineverse.cineversebackend.info.board.service;

import com.cineverse.cineversebackend.info.board.dto.InfoBoardDTO;
import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface InfoBoardService {


    InfoBoard deleteInfo(int infoId);

    List<InfoBoard> findInfoList();

    InfoBoardDTO findInfoById(int infoId);

    void registInfo(InfoBoardDTO newInfo, MultipartFile[] images);

    void modifyInfo(int infoId, InfoBoardDTO info, MultipartFile[] images);
}
