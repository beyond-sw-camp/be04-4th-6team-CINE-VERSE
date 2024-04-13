package com.cineverse.cineversebackend.image.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ImageSaveDTO {
    private List<MultipartFile> images = new ArrayList<>();
}
