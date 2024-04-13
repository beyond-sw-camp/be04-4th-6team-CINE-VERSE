package com.cineverse.cineversebackend.free.board.service;

import com.cineverse.cineversebackend.free.board.dto.FreeBoardDTO;
import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import com.cineverse.cineversebackend.image.entity.Image;
import com.cineverse.cineversebackend.free.board.repo.FreeBoardRepository;
import com.cineverse.cineversebackend.image.repo.ImageRepository;
import com.cineverse.cineversebackend.image.service.ImageService;
import com.cineverse.cineversebackend.point.service.PointService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FreeBoardServiceImpl implements FreeBoardService{

    private final ModelMapper modelMapper;
    private final FreeBoardRepository freeBoardRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final PointService pointService;

    @Autowired
    public FreeBoardServiceImpl(ModelMapper modelMapper,
                                FreeBoardRepository freeBoardRepository,
                                ImageService imageService,
                                ImageRepository imageRepository,
                                PointService pointService) {
        this.modelMapper = modelMapper;
        this.freeBoardRepository = freeBoardRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.pointService = pointService;
    }

    @Override
    public void registFree(FreeBoardDTO newBoard, MultipartFile[] images) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);
        newBoard.setFreeDate(registDate);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        FreeBoard freeBoard = modelMapper.map(newBoard, FreeBoard.class);
        freeBoardRepository.save(freeBoard);

        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                String url = imageService.saveImage(image, freeBoard);
            }
        }

        pointService.addBoardPoint(freeBoard.getMember().getMemberId());
    }

    @Override
    @Transactional
    public void modifyFree(int freeId, FreeBoardDTO free, MultipartFile[] images) {
        FreeBoard freeBoard = freeBoardRepository.findById(freeId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 게시글입니다."));

        if (freeBoard.getFreeTitle() != null) {
            freeBoard.setFreeTitle(free.getFreeTitle());
        }

        if (freeBoard.getFreeContent() != null) {
            freeBoard.setFreeContent(free.getFreeContent());
        }

        if (images != null && images.length > 0) {
            imageService.deleteImagesByFree(freeBoard);
            for (MultipartFile image : images) {
                imageService.saveImage(image, freeBoard);
            }
        }
    }

    @Override
    public FreeBoard modifyFreeDeleteDate(int freeId) {
        Optional<FreeBoard> optionalFreeBoard = freeBoardRepository.findById(freeId);
        if (optionalFreeBoard.isEmpty()) {
            throw new EntityNotFoundException("게시글이 존재하지 않습니다.");
        }

        FreeBoard freeBoard = optionalFreeBoard.get();

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String deleteDate = format.format(date);

        freeBoard.setFreeDeleteDate(deleteDate);

        return freeBoardRepository.save(freeBoard);
    }

    @Override
    public List<FreeBoard> findFreeList() {
        List<FreeBoard> freeBoardList = freeBoardRepository.findByFreeDeleteDateIsNullOrderByFreeIdDesc();

        return freeBoardList.stream().map(free ->modelMapper
                .map(free, FreeBoard.class))
                .collect(Collectors.toList());
    }

    @Override
    public FreeBoardDTO findFreeById(int freeId) {
        FreeBoard freeBoard=freeBoardRepository.findById(freeId)
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾지 못했습니다."));

        List<Image> images = imageRepository.findByFree_FreeId(freeId);

        freeBoard.setImages(images);

        freeBoard.setFreeViewCount(freeBoard.getFreeViewCount() + 1);
        freeBoardRepository.save(freeBoard);

        return modelMapper.map(freeBoard, FreeBoardDTO.class);
    }
}
