package com.cineverse.cineversebackend.review.comment.service;

import com.cineverse.cineversebackend.review.comment.dto.ReviewCommentDTO;
import com.cineverse.cineversebackend.review.comment.entity.ReviewComment;
import com.cineverse.cineversebackend.review.comment.repo.ReviewCommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewCommentServiceImpl implements ReviewCommentService {

    private final ModelMapper modelMapper;
    private final ReviewCommentRepository reviewCommentRepository;

    @Autowired
    public ReviewCommentServiceImpl(ModelMapper modelMapper, ReviewCommentRepository reviewCommentRepository) {
        this.modelMapper = modelMapper;
        this.reviewCommentRepository = reviewCommentRepository;
    }

    @Override
    public void registReviewComment(ReviewComment reviewComment) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);
        reviewComment.setCommentDate(registDate);

        reviewCommentRepository.save(reviewComment);
    }

    @Override
    public ReviewComment modifyReviewComment(int reviewCommentId, ReviewCommentDTO reviewCommentDTO) {
        Optional<ReviewComment> optionalReviewComment = reviewCommentRepository.findById(reviewCommentId);
        if (optionalReviewComment.isEmpty()) {
            throw new EntityNotFoundException("댓글이 존재하지 않습니다.");
        }

        ReviewComment reviewComment = optionalReviewComment.get();
        reviewComment.setCommentContent(reviewCommentDTO.getCommentContent());

        return reviewCommentRepository.save(reviewComment);
    }

    @Override
    public ReviewComment modifyReviewCommentDeleteDate(int reviewCommentId, ReviewCommentDTO reviewCommentDTO) {
        Optional<ReviewComment> optionalReviewComment = reviewCommentRepository.findById(reviewCommentId);
        if (optionalReviewComment.isEmpty()) {
            throw new EntityNotFoundException("댓글이 존재하지 않습니다.");
        }

        ReviewComment reviewComment = optionalReviewComment.get();

        Date date = new Date();
        SimpleDateFormat format  =new SimpleDateFormat("yyyy-MM-dd");
        String deleteDate = format.format(date);

        reviewComment.setCommentDeleteDate(deleteDate);

        return reviewCommentRepository.save(reviewComment);
    }

    @Override
    public List<ReviewComment> findReviewCommentList() {
        List<ReviewComment> reviewCommentList = reviewCommentRepository
                .findByCommentDeleteDateIsNullOrderByReviewCommentIdDesc();

        return reviewCommentList.stream().map(reviewComment -> modelMapper
                        .map(reviewComment, ReviewComment.class))
                .collect(Collectors.toList());
    }
}
