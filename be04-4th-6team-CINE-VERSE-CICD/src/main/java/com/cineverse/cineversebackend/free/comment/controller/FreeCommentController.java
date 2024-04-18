package com.cineverse.cineversebackend.free.comment.controller;

import com.cineverse.cineversebackend.free.comment.dto.FreeCommentDTO;
import com.cineverse.cineversebackend.free.comment.entity.FreeComment;
import com.cineverse.cineversebackend.free.comment.service.FreeCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "자유 게시글 댓글 작성", description = "자유 게시글 댓글을 작성합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    private ResponseEntity<FreeComment> registFreeComment(
            @Parameter(required = true, description = "댓글 작성 요청")
            @RequestBody FreeCommentDTO freeCommentDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        FreeComment freeComment=modelMapper.map(freeCommentDTO, FreeComment.class);
        freeCommentService.registFreeComment(freeComment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 설명. 댓글 수정 */
    @PatchMapping("/modify/{freeCommentId}")
    @Operation(summary = "자유 게시글 댓글 수정", description = "자유 게시글 댓글을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<FreeComment> modifyFreeComment (
            @Parameter(required = true, description = "댓글 수정 요청")
            @RequestBody FreeCommentDTO freeCommentDTO,
            @Parameter(required = true, description = "댓글 고유 번호")
            @PathVariable int freeCommentId) {
        return ResponseEntity.ok(freeCommentService.modifyFreeComment(freeCommentId, freeCommentDTO));
    }

    /* 설명. 댓글 삭제 (댓글 삭제일 update) */
    @PatchMapping("/delete/{freeCommentId}")
    @Operation(summary = "자유 게시글 댓글 삭제", description = "자유 게시글 댓글을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<FreeComment> deleteFreeComment (
            @Parameter(required = true, description = "댓글 고유 번호")
            @PathVariable int freeCommentId) {
        return ResponseEntity.ok(freeCommentService.modifyFreeCommentDeleteDate(freeCommentId));
    }

    /* 설명. 댓글 전체 조회 */
    @GetMapping("/list")
    @Operation(summary = "자유 게시글 전체 댓글 조회", description = "자유 게시글 전체 댓글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    public List<FreeComment> findFreeCommentList() {
        List<FreeComment> freeCommentList = freeCommentService.findFreeCommentList();

        return freeCommentList;
    }
}
