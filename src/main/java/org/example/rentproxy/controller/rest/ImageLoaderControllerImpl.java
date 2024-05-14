package org.example.rentproxy.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.example.rentproxy.dto.ImageDto;
import org.example.rentproxy.dto.PostImageDto;
import org.example.rentproxy.service.ImageService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
public class ImageLoaderControllerImpl implements ImageLoaderController {
    private final ImageService imageService;

    public PostImageDto uploadImage(long postId, List<MultipartFile> files) {
        List<ImageDto> imageDtos = files.stream().
                map(file -> {
                    ImageDto imageDto = new ImageDto();
                    try {
                        imageDto.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                    return imageDto;
                }).toList();

        return imageService.uploadImages(postId, imageDtos);
    }
}
