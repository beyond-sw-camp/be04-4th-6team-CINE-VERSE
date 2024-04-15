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

    InfoBoard registInfo(InfoBoardDTO newInfo, MultipartFile[] images);

    InfoBoard modifyInfo(int infoId, InfoBoardDTO info, MultipartFile[] images);
}
