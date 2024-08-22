package com.cineverse.cineversebackend.image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.cineverse.cineversebackend.free.board.entity.FreeBoard;
import com.cineverse.cineversebackend.image.entity.Image;
import com.cineverse.cineversebackend.image.repo.ImageRepository;
import com.cineverse.cineversebackend.info.board.entity.InfoBoard;
import com.cineverse.cineversebackend.review.board.entity.ReviewBoard;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private static String bucketName = "cineverse";

    private final AmazonS3Client amazonS3Client;
    private final ImageRepository imageRepository;

//    public List<String> saveImages(ImageSaveDTO saveDTO) {
//        List<String> resultList = new ArrayList<>();
//
//        for (MultipartFile multipartFile : saveDTO.getImages()) {
//            String value = saveImage(multipartFile);
//            resultList.add(value);
//        }
//
//        return resultList;
//    }

    @Transactional
    public String saveImage(MultipartFile multipartFile, ReviewBoard reviewBoard) {
        String originalName = multipartFile.getOriginalFilename();
        String storedName = UUID.randomUUID().toString();

        Image image = new Image();
        image.setOriginName(originalName);
        image.setStoredName(storedName);
        image.setReview(reviewBoard);

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, storedName, multipartFile.getInputStream(), objectMetadata);

            String accessUrl = amazonS3Client.getUrl(bucketName, storedName).toString();
            image.setAccessUrl(accessUrl);
            imageRepository.save(image);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return image.getAccessUrl();
    }

    public String saveImage(MultipartFile multipartFile, FreeBoard freeBoard) {
        String originalName = multipartFile.getOriginalFilename();
        String storedName = UUID.randomUUID().toString(); // 실제 파일 저장을 위한 유니크한 이름 생성

        Image image = new Image();
        image.setOriginName(originalName);
        image.setStoredName(storedName);
        image.setFree(freeBoard); // 여기서 Board와 연결

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, storedName, multipartFile.getInputStream(), objectMetadata); // S3에 파일 저장

            String accessUrl = amazonS3Client.getUrl(bucketName, storedName).toString();
            image.setAccessUrl(accessUrl);
            imageRepository.save(image); // 이미지 정보를 데이터베이스에 저장

        } catch (IOException e) {
            // 적절한 예외 처리 필요
            e.printStackTrace();
        }

        return image.getAccessUrl();
    }

    public String saveImage(MultipartFile multipartFile, InfoBoard infoBoard) {
        String originalName = multipartFile.getOriginalFilename();
        String storedName = UUID.randomUUID().toString(); // 실제 파일 저장을 위한 유니크한 이름 생성

        Image image = new Image();
        image.setOriginName(originalName);
        image.setStoredName(storedName);
        image.setInfo(infoBoard); // 여기서 ReviewBoard와 연결

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, storedName, multipartFile.getInputStream(), objectMetadata); // S3에 파일 저장

            String accessUrl = amazonS3Client.getUrl(bucketName, storedName).toString();
            image.setAccessUrl(accessUrl);
            imageRepository.save(image); // 이미지 정보를 데이터베이스에 저장

        } catch (IOException e) {
            // 적절한 예외 처리 필요
            e.printStackTrace();
        }

        return image.getAccessUrl();
    }

    @Transactional
    public void deleteImagesByReview(ReviewBoard reviewBoard) {
        List<Image> images = imageRepository.findByReview_ReviewId(reviewBoard.getReviewId());
        for (Image image : images) {
            imageRepository.delete(image);
            amazonS3Client.deleteObject(bucketName, image.getStoredName());
        }
    }

    @Transactional
    public void deleteImagesByInfo(InfoBoard infoBoard) {
        List<Image> images = imageRepository.findByInfo_InfoId(infoBoard.getInfoId());
        for (Image image : images) {
            imageRepository.delete(image);
            amazonS3Client.deleteObject(bucketName, image.getStoredName());
        }
    }

    @Transactional
    public void deleteImagesByFree(FreeBoard freeBoard) {
        List<Image> images = imageRepository.findByFree_FreeId(freeBoard.getFreeId());
        for (Image image : images) {
            imageRepository.delete(image);
            amazonS3Client.deleteObject(bucketName, image.getStoredName());
        }
    }
}
