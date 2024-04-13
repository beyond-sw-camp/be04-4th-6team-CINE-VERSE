package com.cineverse.cineversebackend.free.board.controller;

import com.cineverse.cineversebackend.free.board.dto.FreeBoardDTO;
import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import com.cineverse.cineversebackend.free.board.service.FreeBoardService;
import com.cineverse.cineversebackend.review.board.dto.ReviewBoardDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ResponseEntity<FreeBoardDTO> registFree(
            @RequestPart("freeBoard") String freeBoardJson,
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
    public ResponseEntity<FreeBoard> modifyFreeBoard
    (@RequestPart("free") String freeJson,
     @RequestPart(value = "images", required = false) MultipartFile[] images,
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
    public ResponseEntity<FreeBoard> deleteFreeBoard(@PathVariable int freeId) {
        return ResponseEntity.ok(freeBoardService.modifyFreeDeleteDate(freeId));
    }

    /* 설명. 게시글 전체 조회 */
    @GetMapping("/list")
    public List<FreeBoard> findFreeList() {
        List<FreeBoard> freeBoardList = freeBoardService.findFreeList();

        return freeBoardList;
    }

    /* 설명. 게시글 단일 조회 */
    @GetMapping("/{freeId}")
    public FreeBoardDTO findFreeByID(@PathVariable int freeId) {
        FreeBoardDTO free = freeBoardService.findFreeById(freeId);

        return free;
    }
}
