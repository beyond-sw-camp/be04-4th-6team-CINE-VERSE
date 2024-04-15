package com.cineverse.cineversebackend.info.board.service;

import com.cineverse.cineversebackend.image.entity.Image;
import com.cineverse.cineversebackend.image.repo.ImageRepository;
import com.cineverse.cineversebackend.image.service.ImageService;
import com.cineverse.cineversebackend.info.board.dto.InfoBoardDTO;
import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
import com.cineverse.cineversebackend.info.board.repo.InfoBoardRepository;
import com.cineverse.cineversebackend.point.service.PointService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InfoBoardServiceImpl implements InfoBoardService{

    private final ModelMapper modelMapper;
    private final InfoBoardRepository infoBoardRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final PointService pointService;

    @Autowired
    public InfoBoardServiceImpl(ModelMapper modelMapper,
                                InfoBoardRepository infoBoardRepository,
                                ImageService imageService,
                                ImageRepository imageRepository,
                                PointService pointService) {
        this.modelMapper = modelMapper;
        this.infoBoardRepository = infoBoardRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.pointService = pointService;
    }

    @Override
    public InfoBoard registInfo(InfoBoardDTO newInfo, MultipartFile[] images) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);
        newInfo.setInfoDate(registDate);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        InfoBoard infoBoard = modelMapper.map(newInfo, InfoBoard.class);
        infoBoardRepository.save(infoBoard);

        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                String url = imageService.saveImage(image, infoBoard);
            }
        }

        pointService.addBoardPoint(infoBoard.getMember().getMemberId());
        return infoBoard;
    }

    @Override
    @Transactional
    public InfoBoard modifyInfo(int infoId, InfoBoardDTO info, MultipartFile[] images) {
        InfoBoard infoBoard = infoBoardRepository.findById(infoId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다."));

        if (infoBoard.getInfoTitle() != null) {
            infoBoard.setInfoTitle(info.getInfoTitle());
        }

        if (infoBoard.getInfoContent() != null) {
            infoBoard.setInfoContent(info.getInfoContent());
        }

        if (infoBoard.getInfoCategory() != null) {
            infoBoard.setInfoCategory(info.getInfoCategory());
        }

        if (images != null && images.length > 0) {
            imageService.deleteImagesByInfo(infoBoard);
            for (MultipartFile image : images) {
                imageService.saveImage(image, infoBoard);
            }
        }

        infoBoardRepository.save(infoBoard);
        return infoBoard;
    }

    @Override
    public InfoBoard deleteInfo(int infoId) {
        Optional<InfoBoard> optionalInfoBoard = infoBoardRepository.findById(infoId);
        if (optionalInfoBoard.isEmpty()) {
            throw new EntityNotFoundException("존재하지 않는 게시글입니다.");
        }

        InfoBoard infoBoard = optionalInfoBoard.get();

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String deleteDate = format.format(date);
        infoBoard.setInfoDeleteDate(deleteDate);

        return infoBoardRepository.save(infoBoard);
    }

    @Override
    public List<InfoBoard> findInfoList() {
        List<InfoBoard> infoBoardList = infoBoardRepository.findByInfoDeleteDateIsNullOrderByInfoIdDesc();

        return infoBoardList.stream().map(infoBoard -> modelMapper
                .map(infoBoard, InfoBoard.class))
                .collect(Collectors.toList());
    }

    @Override
    public InfoBoardDTO findInfoById(int infoId) {
        InfoBoard infoBoard = infoBoardRepository.findById(infoId)
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾지 못했습니다."));

        List<Image> images = imageRepository.findByInfo_InfoId(infoId);

        infoBoard.setImages(images);

        infoBoard.setInfoViewCount(infoBoard.getInfoViewCount() + 1);
        infoBoardRepository.save(infoBoard);

        return modelMapper.map(infoBoard, InfoBoardDTO.class);
    }
}
