package org.example.rentproxy.mapper;

import org.example.rentproxy.dto.ImageDto;
import org.example.rentproxy.repository.mongo.documents.Image;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageMapper extends Mapper {
    public Image convertToImage(ImageDto imageDto) {
        if (imageDto == null) {
            return null;
        }
        return map(imageDto, Image.class);
    }

    public List<Image> convertToImageList(List<ImageDto> imageDtoList) {
        if (imageDtoList == null) {
            return null;
        }
        return convertToList(imageDtoList, this::convertToImage);
    }
}
