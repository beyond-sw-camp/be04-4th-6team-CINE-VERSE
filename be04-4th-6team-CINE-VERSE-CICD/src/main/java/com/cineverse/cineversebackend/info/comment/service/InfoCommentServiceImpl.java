package com.cineverse.cineversebackend.info.comment.service;

import com.cineverse.cineversebackend.info.comment.dto.InfoCommentDTO;
import com.cineverse.cineversebackend.info.comment.entity.InfoComment;
import com.cineverse.cineversebackend.info.comment.repo.InfoCommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InfoCommentServiceImpl implements InfoCommentService{

    private final ModelMapper modelMapper;
    private final InfoCommentRepository infoCommentRepository;

    @Autowired
    public InfoCommentServiceImpl(ModelMapper modelMapper, InfoCommentRepository infoCommentRepository) {
        this.modelMapper = modelMapper;
        this.infoCommentRepository = infoCommentRepository;
    }

    @Override
    public InfoComment registInfoComment(InfoComment infoComment) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);
        infoComment.setCommentDate(registDate);

        infoCommentRepository.save(infoComment);
        return infoComment;
    }

    @Override
    public InfoComment modifyInfoComment(int infoCommentId, InfoCommentDTO infoCommentDTO) {
        Optional<InfoComment> optionalInfoComment=infoCommentRepository.findById(infoCommentId);
        if(optionalInfoComment.isEmpty()) {
            throw new EntityNotFoundException("댓글이 존재하지 않습니다.");
        }

        InfoComment infoComment = optionalInfoComment.get();
        infoComment.setCommentContent(infoCommentDTO.getCommentContent());

        return infoCommentRepository.save(infoComment);
    }

    @Override
    public InfoComment modifyInfoCommentDeleteDate(int infoCommentId) {
        Optional<InfoComment> optionalInfoComment = infoCommentRepository.findById(infoCommentId);
        if (optionalInfoComment.isEmpty()) {
            throw new EntityNotFoundException("게시글이 존재하지 않습니다.");
        }

        InfoComment infoComment = optionalInfoComment.get();

        Date date = new Date();
        SimpleDateFormat format  =new SimpleDateFormat("yyyy-MM-dd");
        String deleteDate = format.format(date);

        infoComment.setCommentDeleteDate(deleteDate);

        return infoCommentRepository.save(infoComment);
    }

    @Override
    public List<InfoComment> findInfoCommentList() {
        List<InfoComment> infoCommentList = infoCommentRepository
                .findByCommentDeleteDateIsNullOrderByInfoCommentIdDesc();
        
        return infoCommentList.stream().map(infoComment -> modelMapper
                        .map(infoComment, InfoComment.class))
                .collect(Collectors.toList());
    }
}
