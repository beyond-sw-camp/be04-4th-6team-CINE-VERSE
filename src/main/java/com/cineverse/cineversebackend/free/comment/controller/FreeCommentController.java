package com.cineverse.cineversebackend.free.comment.controller;

import com.cineverse.cineversebackend.free.comment.dto.FreeCommentDTO;
import com.cineverse.cineversebackend.free.comment.entity.FreeComment;
import com.cineverse.cineversebackend.free.comment.service.FreeCommentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/free_comment")
public class FreeCommentController {

    private FreeCommentService freeCommentService;
    private ModelMapper modelMapper;

    @Autowired
    public FreeCommentController(FreeCommentService freeCommentService, ModelMapper modelMapper) {
        this.freeCommentService = freeCommentService;
        this.modelMapper = modelMapper;
    }

    /* 설명. 댓글 작성 */
    @PostMapping("/regist")
    private ResponseEntity<FreeComment> registFreeComment(@RequestBody FreeCommentDTO freeCommentDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        FreeComment freeComment=modelMapper.map(freeCommentDTO, FreeComment.class);
        freeCommentService.registFreeComment(freeComment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 설명. 댓글 수정 */
    @PatchMapping("/modify/{freeCommentId}")
    public ResponseEntity<FreeComment> modifyFreeComment (@RequestBody FreeCommentDTO freeCommentDTO, @PathVariable int freeCommentId) {
        return ResponseEntity.ok(freeCommentService.modifyFreeComment(freeCommentId, freeCommentDTO));
    }

    /* 설명. 댓글 삭제 (댓글 삭제일 update) */
    @PatchMapping("/delete/{freeCommentId}")
    public ResponseEntity<FreeComment> deleteFreeComment (@RequestBody FreeCommentDTO freeCommentDTO, @PathVariable int freeCommentId) {
        return ResponseEntity.ok(freeCommentService.modifyFreeCommentDeleteDate(freeCommentId, freeCommentDTO));
    }

    /* 설명. 댓글 전체 조회 */
    @GetMapping("/list")
    public List<FreeComment> findFreeCommentList() {
        List<FreeComment> freeCommentList = freeCommentService.findFreeCommentList();

        return freeCommentList;
    }
}
