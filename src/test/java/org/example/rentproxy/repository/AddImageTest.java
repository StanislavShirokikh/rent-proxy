package org.example.rentproxy.repository;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.example.rentproxy.repository.mongo.PostImageRepository;
import org.example.rentproxy.repository.mongo.documents.Image;
import org.example.rentproxy.repository.mongo.ImageRepository;
import org.example.rentproxy.repository.mongo.documents.PostImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddImageTest {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private PostImageRepository postImageRepository;


    @Test
    void addImage() {
        Image image1 = new Image();
        byte[] imageData1 = {0x00, 0x01, 0x02, 0x03};
        image1.setImage(new Binary(BsonBinarySubType.BINARY, imageData1));
        Image image2 = new Image();
        byte[] imageData2 = {0x00, 0x01, 0x02, 0x03};
        image2.setImage(new Binary(BsonBinarySubType.BINARY, imageData2));
        List<Image> images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        List<Image> savedImages = imageRepository.saveAll(images);

        PostImage postImage = new PostImage();
        postImage.setPostId(1L);
        postImage.setImages(savedImages);

        PostImage savedPostImage = postImageRepository.save(postImage);

        assertEquals(postImage.getPostId(), savedPostImage.getPostId());
    }

    @Test
    void findPostImagesByPostId() {
        PostImage postImage = postImageRepository.findByPostId(1L);

        assertEquals(1L, postImage.getPostId());
    }
}
