package com.cineverse.cineversebackend.image.entity;

import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tbl_image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "origin_name")
    private String originName;

    @Column(name = "stored_name")
    private String storedName;

    @Column(name = "access_url")
    private String accessUrl;

    @ManyToOne
    @JoinColumn(name = "free_id")
    @JsonIgnore
    private FreeBoard free;

    @ManyToOne
    @JoinColumn(name = "review_id")
    @JsonIgnore
    private ReviewBoard review;

    @ManyToOne
    @JoinColumn(name = "info_id")
    @JsonIgnore
    private InfoBoard info;

    @Column(name = "event_board_id")
    private Integer eventBoardId;

    public Image(String originName) {
        this.originName = originName;
        this.storedName = getFileName(originName);
        this.accessUrl = "";
    }

    public Image(Long id, String originName, String storedName, String accessUrl, FreeBoard free, ReviewBoard review, InfoBoard info, Integer eventBoardId) {
        this.id = id;
        this.originName = originName;
        this.storedName = storedName;
        this.accessUrl = accessUrl;
        this.free = free;
        this.review = review;
        this.info = info;
        this.eventBoardId = eventBoardId;
    }

    /* 설명. 이미지 파일의 확장자를 추출 */
    public String extractExtension(String originName) {
        int index = originName.lastIndexOf('.');

        return originName.substring(index, originName.length());
    }

    /* 설명. 이미지 파일의 이름을 저장하기 위한 이름으로 변환하는 메소드 */
    public String getFileName(String originName) {
        return UUID.randomUUID() + "." + extractExtension(originName);
    }
}
