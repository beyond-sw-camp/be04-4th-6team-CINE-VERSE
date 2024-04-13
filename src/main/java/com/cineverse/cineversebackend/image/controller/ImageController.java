package com.cineverse.cineversebackend.image.controller;

import com.cineverse.cineversebackend.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {

    private final ImageService imageService;

//    @PostMapping("/image")
//    @ResponseStatus(HttpStatus.OK)
//    public List<String> saveImage(@ModelAttribute ImageSaveDTO imageSaveDTO) {
////        return imageService.saveImages(imageSaveDTO);
//    }
}
