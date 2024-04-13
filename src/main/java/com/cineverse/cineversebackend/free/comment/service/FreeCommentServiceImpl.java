package com.cineverse.cineversebackend.free.comment.service;

import com.cineverse.cineversebackend.free.comment.dto.FreeCommentDTO;
import com.cineverse.cineversebackend.free.comment.entity.FreeComment;
import com.cineverse.cineversebackend.free.comment.repo.FreeCommentRepository;
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
public class FreeCommentServiceImpl implements FreeCommentService{

    private final ModelMapper modelMapper;
    private final FreeCommentRepository freeCommentRepository;

    @Autowired
    public FreeCommentServiceImpl(ModelMapper modelMapper, FreeCommentRepository freeCommentRepository) {
        this.modelMapper = modelMapper;
        this.freeCommentRepository = freeCommentRepository;
    }

    @Override
    public void registFreeComment(FreeComment freeComment) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = format.format(date);
        freeComment.setCommentDate(registDate);

        freeCommentRepository.save(freeComment);
    }

    @Override
    public FreeComment modifyFreeComment(int freeCommentId, FreeCommentDTO freeCommentDTO) {
        Optional<FreeComment> optionalFreeComment=freeCommentRepository.findById(freeCommentId);
        if(optionalFreeComment.isEmpty()) {
            throw new EntityNotFoundException("댓글이 존재하지 않습니다.");
        }

        FreeComment freeComment=optionalFreeComment.get();
        freeComment.setCommentContent(freeCommentDTO.getCommentContent());

        return freeCommentRepository.save(freeComment);
    }

    @Override
    public FreeComment modifyFreeCommentDeleteDate(int freeCommentId, FreeCommentDTO freeCommentDTO) {

        Optional<FreeComment> optionalFreeComment = freeCommentRepository.findById(freeCommentId);
        if (optionalFreeComment.isEmpty()) {
            throw new EntityNotFoundException("게시글이 존재하지 않습니다.");
        }

        FreeComment freeComment = optionalFreeComment.get();

        Date date = new Date();
        SimpleDateFormat format  =new SimpleDateFormat("yyyy-MM-dd");
        String deleteDate = format.format(date);

        freeComment.setCommentDeleteDate(deleteDate);

        return freeCommentRepository.save(freeComment);
    }

    @Override
    public List<FreeComment> findFreeCommentList() {
        List<FreeComment> freeCommentList=freeCommentRepository.findByCommentDeleteDateIsNullOrderByFreeCommentIdDesc();

        return freeCommentList.stream().map(freeComment -> modelMapper
                .map(freeComment, FreeComment.class))
                .collect(Collectors.toList());
    }
}
