package org.example.rentproxy.service;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.example.rentproxy.dto.ImageDto;
import org.example.rentproxy.dto.PostImageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AddImageTest {
    @Autowired
    private ImageService imageService;


    @Test
    void addImage() {
        ImageDto imageDto1 = new ImageDto();
        byte[] imageData1 = {0x00, 0x01, 0x02, 0x03};
        imageDto1.setImage(new Binary(BsonBinarySubType.BINARY, imageData1));

        ImageDto imageDto2 = new ImageDto();
        byte[] imageData2 = {0x00, 0x01, 0x02, 0x03};
        imageDto2.setImage(new Binary(BsonBinarySubType.BINARY, imageData2));

        List<ImageDto> imageDtos = new ArrayList<>();
        imageDtos.add(imageDto2);
        imageDtos.add(imageDto2);

        PostImageDto savedPostImage = imageService.uploadImages(1L, imageDtos);

        assertNotNull(savedPostImage.getId());
        assertEquals(1L, savedPostImage.getPostId());
        savedPostImage.getImages().forEach(imageDto -> assertNotNull(imageDto.getId()));
        savedPostImage.getImages().forEach(imageDto -> assertNotNull(imageDto.getImage()));
    }

    @Test
    void findPostImagesByPostId() {
        PostImageDto postImageDto = imageService.getImageByPostId(1L);

        assertNotNull(postImageDto.getId());
        assertEquals(1L, postImageDto.getPostId());
        postImageDto.getImages().forEach(image -> assertNotNull(image.getImage()));
    }
}
