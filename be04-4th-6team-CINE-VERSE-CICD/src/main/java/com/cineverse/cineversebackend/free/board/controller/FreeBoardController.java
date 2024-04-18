package com.cineverse.cineversebackend.free.board.controller;

import com.cineverse.cineversebackend.free.board.dto.FreeBoardDTO;
import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import com.cineverse.cineversebackend.free.board.service.FreeBoardService;
import com.cineverse.cineversebackend.review.board.dto.ReviewBoardDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(value = "/free_board")
public class FreeBoardController {

    private FreeBoardService freeBoardService;
    private ModelMapper modelMapper;

    @Autowired
    public FreeBoardController(FreeBoardService freeBoardService, ModelMapper modelMapper) {
        this.freeBoardService = freeBoardService;
        this.modelMapper = modelMapper;
    }

    /* 설명. 게시글 작성 */
    @PostMapping("/regist")
    @Operation(summary = "자유 게시글 생성", description = "자유 게시글을 생성합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<FreeBoardDTO> registFree(
            @Parameter(required = true, description = "게시글 데이터")
            @RequestPart("freeBoard") String freeBoardJson,
            @Parameter(required = false, description = "게시글 이미지")
            @RequestPart(value = "images", required = false) MultipartFile[] images) throws JsonProcessingException {
        String utf8Json = new String(freeBoardJson.
                getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        FreeBoardDTO newBoard = objectMapper.readValue(utf8Json, FreeBoardDTO.class);      // json 문자열 매핑

        freeBoardService.registFree(newBoard, images);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBoard);
    }

    /* 설명. 게시글 수정 */
    @PatchMapping("/modify/{freeId}")
    @Operation(summary = "자유 게시글 수정", description = "자유 게시글을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<FreeBoard> modifyFreeBoard(
            @Parameter(required = true, description = "게시글 수정 데이터")
            @RequestPart("free") String freeJson,
            @Parameter(required = false, description = "게시글 이미지")
            @RequestPart(value = "images", required = false) MultipartFile[] images,
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int freeId) throws JsonProcessingException {
        String utf8Json = new String(freeJson.
                getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        FreeBoardDTO freeBoard = objectMapper.readValue(utf8Json, FreeBoardDTO.class);

        freeBoardService.modifyFree(freeId, freeBoard, images);
        return ResponseEntity.ok().build();
    }

    /* 설명. 게시글 삭제 (게시글 삭제일 update) */
    @PatchMapping("/delete/{freeId}")
    @Operation(summary = "자유 게시글 삭제", description = "자유 게시글을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<FreeBoard> deleteFreeBoard(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int freeId) {
        return ResponseEntity.ok(freeBoardService.modifyFreeDeleteDate(freeId));
    }

    /* 설명. 게시글 전체 조회 */
    @GetMapping("/list")
    @Operation(summary = "전체 자유 게시글 조회", description = "전체 자유 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    public List<FreeBoard> findFreeList() {
        List<FreeBoard> freeBoardList = freeBoardService.findFreeList();

        return freeBoardList;
    }

    /* 설명. 게시글 단일 조회 */
    @GetMapping("/{freeId}")
    @Operation(summary = "단일 자유 게시글 조회", description = "단일 자유 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public FreeBoardDTO findFreeByID(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int freeId) {
        FreeBoardDTO free = freeBoardService.findFreeById(freeId);

        return free;
    }
}
