package com.cineverse.cineversebackend.like.service;

import com.cineverse.cineversebackend.like.dto.PostLikeDTO;
import com.cineverse.cineversebackend.like.entity.PostLike;
import com.cineverse.cineversebackend.like.repo.PostLikeRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostLikeServiceImpl implements PostLikeService{
    private PostLikeRepository postLikeRepository;
    private ModelMapper mapper;

    @Autowired
    public PostLikeServiceImpl(PostLikeRepository postLikeRepository, ModelMapper mapper) {
        this.postLikeRepository = postLikeRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public void likeReview(int reviewId, int memberId) {
        Optional<PostLike> postLike = postLikeRepository.findByReviewIdAndMemberId(reviewId, memberId);

        if (postLike.isPresent()) {
            postLikeRepository.delete(postLike.get());
        } else {
            PostLike newPostLike = new PostLike();
            newPostLike.setReviewId(reviewId);
            newPostLike.setMemberId(memberId);
            postLikeRepository.save(newPostLike);
        }
    }

    @Override
    @Transactional
    public void likeFree(int freeId, int memberId) {
        Optional<PostLike> postLike = postLikeRepository.findByFreeIdAndMemberId(freeId, memberId);

        if (postLike.isPresent()) {
            postLikeRepository.delete(postLike.get());
        } else {
            PostLike newPostLike = new PostLike();
            newPostLike.setFreeId(freeId);
            newPostLike.setMemberId(memberId);
            postLikeRepository.save(newPostLike);
        }
    }

    @Override
    @Transactional
    public void likeInfo(int infoId, int memberId) {
        Optional<PostLike> postLike = postLikeRepository.findByInfoIdAndMemberId(infoId, memberId);

        if (postLike.isPresent()) {
            postLikeRepository.delete(postLike.get());
        } else {
            PostLike newPostLike = new PostLike();
            newPostLike.setInfoId(infoId);
            newPostLike.setMemberId(memberId);
            postLikeRepository.save(newPostLike);
        }
    }

    @Override
    @Transactional
    public void likeEvent(int eventId, int memberId) {
        Optional<PostLike> postLike = postLikeRepository.findByEventIdAndMemberId(eventId, memberId);

        if (postLike.isPresent()) {
            postLikeRepository.delete(postLike.get());
        } else {
            PostLike newPostLike = new PostLike();
            newPostLike.setEventId(eventId);
            newPostLike.setMemberId(memberId);
            postLikeRepository.save(newPostLike);
        }
    }

    @Override
    public PostLikeDTO findLikeList(int memberId) {
        PostLike postLike = postLikeRepository.findByMemberId(memberId);

        PostLikeDTO postLikeDTO = mapper.map(postLike, PostLikeDTO.class);

        return postLikeDTO;
    }
}
