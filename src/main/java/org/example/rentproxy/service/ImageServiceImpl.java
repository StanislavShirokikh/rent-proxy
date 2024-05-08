package org.example.rentproxy.service;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.ImageDto;
import org.example.rentproxy.dto.PostImageDto;
import org.example.rentproxy.mapper.ImageMapper;
import org.example.rentproxy.mapper.PostImageDtoMapper;
import org.example.rentproxy.repository.mongo.ImageRepository;
import org.example.rentproxy.repository.mongo.PostImageRepository;
import org.example.rentproxy.repository.mongo.documents.Image;
import org.example.rentproxy.repository.mongo.documents.PostImage;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final PostImageRepository postImageRepository;
    private final ImageMapper imageMapper;
    private final PostImageDtoMapper postImageDtoMapper;

    @Override
    public PostImageDto uploadImages(Long postId, List<ImageDto> imageDto) {
        List<Image> savedImages = imageRepository.saveAll(imageMapper.convertToImageList(imageDto));

        PostImage postImage = new PostImage();
        postImage.setPostId(postId);
        postImage.setImages(savedImages);

        return postImageDtoMapper.convertToPostImageDto(postImageRepository.save(postImage));
    }
}
