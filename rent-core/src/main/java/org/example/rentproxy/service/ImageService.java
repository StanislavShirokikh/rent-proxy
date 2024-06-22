package org.example.rentproxy.service;

import org.example.rentproxy.dto.ImageDto;
import org.example.rentproxy.dto.PostImageDto;

import java.util.List;

public interface ImageService {
    PostImageDto uploadImages(Long postId, List<ImageDto> imageDto);
    PostImageDto getImageByPostId(Long postId);
}
