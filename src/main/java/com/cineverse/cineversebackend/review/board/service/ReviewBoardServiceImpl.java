package com.cineverse.cineversebackend.review.board.service;

import com.cineverse.cineversebackend.image.entity.Image;
import com.cineverse.cineversebackend.image.repo.ImageRepository;
import com.cineverse.cineversebackend.image.service.ImageService;
import com.cineverse.cineversebackend.like.repo.PostLikeRepository;
import com.cineverse.cineversebackend.like.service.PostLikeService;
import com.cineverse.cineversebackend.point.service.PointService;
import com.cineverse.cineversebackend.review.board.dto.ReviewBoardDTO;
import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
import com.cineverse.cineversebackend.review.board.repo.ReviewBoardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ReviewBoardServiceImpl implements ReviewBoardService{

    private final ModelMapper modelMapper;
    private final ReviewBoardRepository reviewBoardRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;
    private final PointService pointService;

    @Autowired
    public ReviewBoardServiceImpl(ModelMapper modelMapper,
                                  ReviewBoardRepository reviewBoardRepository,
                                  ImageService imageService,
                                  ImageRepository imageRepository,
                                  PointService pointService)
    {
        this.modelMapper = modelMapper;
        this.reviewBoardRepository = reviewBoardRepository;
        this.imageService = imageService;
        this.imageRepository = imageRepository;
        this.pointService = pointService;
    }

    /* 설명. 리뷰 작성 */
    @Override
    @Transactional
    public void registReview(ReviewBoardDTO newReview, MultipartFile[] images) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);
        newReview.setReviewDate(registDate);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ReviewBoard reviewBoard = modelMapper.map(newReview, ReviewBoard.class);
        reviewBoardRepository.save(reviewBoard);

        for (MultipartFile image : images) {
            if (!image.isEmpty()) {
                String url = imageService .saveImage(image, reviewBoard);
            }
        }

        pointService.addBoardPoint(reviewBoard.getMember().getMemberId());
    }

    /* 설명. 단일 리뷰 조회 */
    @Override
    public ReviewBoardDTO findReviewById(int boardId) {
        ReviewBoard reviewBoard = reviewBoardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("리뷰를 찾지 못했습니다."));

        List<Image> images = imageRepository.findByReview_ReviewId(boardId);

        reviewBoard.setImages(images);

        reviewBoard.setReviewViewCount(reviewBoard.getReviewViewCount() + 1);
        reviewBoardRepository.save(reviewBoard);

        return modelMapper.map(reviewBoard, ReviewBoardDTO.class);
    }

    /* 설명. 전체 리뷰 조회 */
    @Override
    public List<ReviewBoard> findReivewList() {
        List<ReviewBoard> reviewList = reviewBoardRepository.findByReviewDeleteDateIsNullOrderByReviewIdDesc();

        return reviewList.stream().map(review -> modelMapper.
                map(review, ReviewBoard.class)).collect(Collectors.toList());
    }

    /* 설명. 리뷰 수정 */
    @Override
    @Transactional
    public void modifyReview(int boardId, ReviewBoardDTO review, MultipartFile[] images) {
        ReviewBoard reviewBoard = reviewBoardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 리뷰입니다."));

        if (review.getReviewTitle() != null) {
            reviewBoard.setReviewTitle(review.getReviewTitle());
        }

        if (review.getReviewContent() != null) {
            reviewBoard.setReviewContent(review.getReviewContent());
        }

        if (review.getReviewCategory() != null) {
            reviewBoard.setReviewCategory(review.getReviewCategory());
        }

        if (review.getReviewStar() != 0) {
            reviewBoard.setReviewStar(review.getReviewStar());
        }

        if (images != null && images.length > 0) {
            imageService.deleteImagesByReview(reviewBoard); // 기존 이미지 삭제
            for (MultipartFile image : images) {
                imageService.saveImage(image, reviewBoard); // 새 이미지 저장
            }
        }

        reviewBoardRepository.save(reviewBoard);
    }

    /* 설명. 게시글 삭제 */
    @Override
    public ReviewBoard deleteReview(int boardId) {
        Optional<ReviewBoard> optionalReview = reviewBoardRepository.findById(boardId);
        if (!optionalReview.isPresent()) {
            throw new EntityNotFoundException("존재하지 않는 리뷰입니다.");
        }

        ReviewBoard reviewBoard = optionalReview.get();

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = format.format(date);
        reviewBoard.setReviewDeleteDate(currentDate);

        return reviewBoardRepository.save(reviewBoard);
    }
}
