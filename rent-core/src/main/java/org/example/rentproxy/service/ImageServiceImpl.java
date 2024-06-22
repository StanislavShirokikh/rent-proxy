package org.example.rentproxy.service;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.ImageDto;
import org.example.rentproxy.dto.PostImageDto;
import org.example.rentproxy.mapper.ImageMapper;
import org.example.rentproxy.mapper.PostImageDtoMapper;
import org.example.rentproxy.repository.mongo.PostImageRepository;
import org.example.rentproxy.repository.mongo.documents.Image;
import org.example.rentproxy.repository.mongo.documents.PostImage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final PostImageRepository postImageRepository;
    private final ImageMapper imageMapper;
    private final PostImageDtoMapper postImageDtoMapper;

    @Override
    public PostImageDto uploadImages(Long postId, List<ImageDto> imageDto) {
        List<Image> images = imageMapper.convertToImageList(imageDto);

        images.forEach(image -> image.setId(String.valueOf(UUID.randomUUID())));

        PostImage postImage = new PostImage();
        postImage.setPostId(postId);
        postImage.setImages(images);

        return postImageDtoMapper.convertToPostImageDto(postImageRepository.save(postImage));
    }

    @Override
    public PostImageDto getImageByPostId(Long postId) {
        return postImageDtoMapper.convertToPostImageDto(postImageRepository.findByPostId(postId));
    }
}
