package org.example.rentproxy.mapper;

import org.example.rentproxy.dto.ImageDto;
import org.example.rentproxy.repository.mongo.documents.Image;
import org.example.rentproxy.dto.ImageDto;
import org.example.rentproxy.repository.mongo.documents.Image;
import org.springframework.stereotype.Component;
import org.example.rentproxy.dto.ImageDto;
import org.example.rentproxy.repository.mongo.documents.Image;

import java.util.List;

@Component
public class ImageMapper extends Mapper {
    public Image convertToImage(ImageDto imageDto) {
        if (imageDto == null) {
            return null;
        }
        Image image = new Image();
        image.setImage(imageDto.getImage());

        return image;
    }

    public List<Image> convertToImageList(List<ImageDto> imageDtoList) {
        if (imageDtoList == null) {
            return null;
        }
        return convertToList(imageDtoList, this::convertToImage);
    }
}
