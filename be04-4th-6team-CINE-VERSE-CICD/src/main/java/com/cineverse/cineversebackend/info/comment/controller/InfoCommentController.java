package com.cineverse.cineversebackend.info.comment.controller;

import com.cineverse.cineversebackend.info.comment.dto.InfoCommentDTO;
import com.cineverse.cineversebackend.info.comment.entity.InfoComment;
import com.cineverse.cineversebackend.info.comment.service.InfoCommentService;
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
    @Operation(summary = "정보 게시글 댓글 작성", description = "정보 게시글 댓글을 작성합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    private ResponseEntity<InfoComment> registInfoComment(
            @Parameter(required = true, description = "댓글 작성 요청")
            @RequestBody InfoCommentDTO infoCommentDTO) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        InfoComment infoComment = modelMapper.map(infoCommentDTO, InfoComment.class);
        infoCommentService.registInfoComment(infoComment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 설명. 댓글 수정 */
    @PatchMapping("/modify/{infoCommentId}")
    @Operation(summary = "정보 게시글 댓글 수정", description = "정보 게시글 댓글을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<InfoComment> modifyInfoComment(
            @Parameter(required = true, description = "댓글 수정 요청")
            @RequestBody InfoCommentDTO infoCommentDTO,
            @Parameter(required = true, description = "댓글 고유 번호")
            @PathVariable int infoCommentId) {
        return ResponseEntity.ok(infoCommentService.modifyInfoComment(infoCommentId, infoCommentDTO));
    }

    /* 설명. 댓글 삭제 */
    @PatchMapping("/delete/{infoCommentId}")
    @Operation(summary = "정보 게시글 댓글 삭제", description = "정보 게시글 댓글을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<InfoComment> deleteInfoComment(
            @Parameter(required = true, description = "댓글 고유 번호")
            @PathVariable int infoCommentId) {
        return ResponseEntity.ok(infoCommentService.modifyInfoCommentDeleteDate(infoCommentId));
    }

    /* 설명. 댓글 전체 조회 */
    @GetMapping("/list")
    @Operation(summary = "정보 게시글 전체 댓글 조회", description = "정보 게시글 전체 댓글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    public List<InfoComment> findInfoCommentList() {
        List<InfoComment> infoComments = infoCommentService.findInfoCommentList();

        return infoComments;
    }
}
