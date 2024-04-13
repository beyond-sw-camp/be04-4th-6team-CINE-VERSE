package com.cineverse.cineversebackend.info.comment.controller;

import com.cineverse.cineversebackend.info.comment.dto.InfoCommentDTO;
import com.cineverse.cineversebackend.info.comment.entity.InfoComment;
import com.cineverse.cineversebackend.info.comment.service.InfoCommentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info_comment")
public class InfoCommentController {

    private InfoCommentService infoCommentService;
    private ModelMapper modelMapper;

    @Autowired
    public InfoCommentController(InfoCommentService infoCommentService, ModelMapper modelMapper) {
        this.infoCommentService = infoCommentService;
        this.modelMapper = modelMapper;
    }

    /* 설명. 댓글 작성 */
    @PostMapping("/regist")
    private ResponseEntity<InfoComment> registInfoComment(@RequestBody InfoCommentDTO infoCommentDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        InfoComment infoComment = modelMapper.map(infoCommentDTO, InfoComment.class);
        infoCommentService.registInfoComment(infoComment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 설명. 댓글 수정 */
    @PatchMapping("/modify/{infoCommentId}")
    public ResponseEntity<InfoComment> modifyInfoComment(@RequestBody InfoCommentDTO infoCommentDTO,
                                                         @PathVariable int infoCommentId) {
        return ResponseEntity.ok(infoCommentService.modifyInfoComment(infoCommentId, infoCommentDTO));
    }

    /* 설명. 댓글 삭제 */
    @PatchMapping("/delete/{infoCommentId}")
    public ResponseEntity<InfoComment> deleteInfoComment(@RequestBody InfoCommentDTO infoCommentDTO,
                                                         @PathVariable int infoCommentId) {
        return ResponseEntity.ok(infoCommentService.modifyInfoCommentDeleteDate(infoCommentId, infoCommentDTO));
    }

    /* 설명. 댓글 전체 조회 */
    @GetMapping("/list")
    public List<InfoComment> findInfoCommentList() {
        List<InfoComment> infoComments = infoCommentService.findInfoCommentList();

        return infoComments;
    }
}
