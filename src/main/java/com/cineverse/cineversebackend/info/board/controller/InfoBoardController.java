package com.cineverse.cineversebackend.info.board.controller;

import com.cineverse.cineversebackend.info.board.dto.InfoBoardDTO;
import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
import com.cineverse.cineversebackend.info.board.service.InfoBoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/info_board")
public class InfoBoardController {

    private final InfoBoardService infoBoardService;

    @Autowired
    public InfoBoardController(InfoBoardService infoBoardService) {
        this.infoBoardService = infoBoardService;
    }

    /* 설명. 정보 게시글 생성 */
    @PostMapping("/regist")
    @Operation(summary = "정보 게시글 작성", description = "정보 게시글을 작성합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<InfoBoardDTO> registInfo(
            @Parameter(required = true, description = "게시글 작성 요청")
            @RequestPart("infoBoard") String infoBoardJson,
            @Parameter(required = false, description = "게시글 이미지")
            @RequestPart(value = "images", required = false)MultipartFile[] images) throws JsonProcessingException {
        String utf8Json = new String(infoBoardJson.
                getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        InfoBoardDTO newInfo = objectMapper.readValue(utf8Json, InfoBoardDTO.class);

        infoBoardService.registInfo(newInfo, images);
        return ResponseEntity.status(HttpStatus.CREATED).body(newInfo);
    }

    /* 설명. 정보 게시글 수정 */
    @PatchMapping("/modify/{infoId}")
    @Operation(summary = "정보 게시글 수정", description = "정보 게시글을 수정합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<InfoBoard> modifyInfo (
            @Parameter(required = true, description = "게시글 수정 요청")
            @RequestPart("info") String infoJson,
            @Parameter(required = false, description = "게시글 이미지")
            @RequestPart(value = "images", required = false) MultipartFile[] images,
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int infoId) throws JsonProcessingException {
        String utf8Json = new String(infoJson.
                getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        InfoBoardDTO info = objectMapper.readValue(utf8Json, InfoBoardDTO.class);
        infoBoardService.modifyInfo(infoId, info, images);
        return ResponseEntity.ok().build();
    }

    /* 설명. 정보 게시글 삭제 */
    @PatchMapping("/delete/{infoId}")
    @Operation(summary = "정보 게시글 삭제", description = "정보 게시글을 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public ResponseEntity<InfoBoard> deleteInfo(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int infoId) {
        return ResponseEntity.ok(infoBoardService.deleteInfo(infoId));
    }

    /* 설명. 정보 게시글 전체 조회 */
    @GetMapping("/list")
    @Operation(summary = "전체 정보 게시글 조회", description = "전체 정보 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    public List<InfoBoard> findInfoList() {
        List<InfoBoard> infoList = infoBoardService.findInfoList();

        return infoList;
    }

    /* 설명. 정보 게시글 단일 조회 */
    @GetMapping("/{infoId}")
    @Operation(summary = "단일 정보 게시글 조회", description = "단일 정보 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "400", description = "파라미터 오류")
    public InfoBoardDTO findInfoById(
            @Parameter(required = true, description = "게시글 고유 번호")
            @PathVariable int infoId) {
        InfoBoardDTO info = infoBoardService.findInfoById(infoId);

        return info;
    }

}
