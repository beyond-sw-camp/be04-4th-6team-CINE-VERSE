package com.cineverse.cineversebackend.info.board.controller;

import com.cineverse.cineversebackend.info.board.dto.InfoBoardDTO;
import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
import com.cineverse.cineversebackend.info.board.service.InfoBoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ResponseEntity<InfoBoardDTO> registInfo(
            @RequestPart("infoBoard") String infoBoardJson,
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
    public ResponseEntity<InfoBoard> modifyInfo (
            @RequestPart("info") String infoJson,
            @RequestPart(value = "images", required = false) MultipartFile[] images,
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
    public ResponseEntity<InfoBoard> deleteInfo(@PathVariable int infoId) {
        return ResponseEntity.ok(infoBoardService.deleteInfo(infoId));
    }

    /* 설명. 정보 게시글 전체 조회 */
    @GetMapping("/list")
    public List<InfoBoard> findInfoList() {
        List<InfoBoard> infoList = infoBoardService.findInfoList();

        return infoList;
    }

    /* 설명. 정보 게시글 단일 조회 */
    @GetMapping("/{infoId}")
    public InfoBoardDTO findInfoById(@PathVariable int infoId) {
        InfoBoardDTO info = infoBoardService.findInfoById(infoId);

        return info;
    }

}
