package org.example.rentproxy.mapper;

import org.example.rentproxy.dto.ImageDto;
import org.example.rentproxy.dto.PostImageDto;
import org.example.rentproxy.repository.mongo.documents.PostImage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostImageDtoMapper extends ImageDtoMapper {

    public PostImageDto convertToPostImageDto(PostImage postImage) {
        if (postImage == null) {
            return null;
        }
        List<ImageDto> imageDtoList = convertToImageDtoList(postImage.getImages());

        PostImageDto postImageDto = map(postImage, PostImageDto.class);
        postImageDto.setImages(imageDtoList);

        return postImageDto;
    }
}
