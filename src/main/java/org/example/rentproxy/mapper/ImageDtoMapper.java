package org.example.rentproxy.mapper;

import org.example.rentproxy.dto.ImageDto;
import org.example.rentproxy.repository.mongo.documents.Image;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageDtoMapper extends Mapper {
    public ImageDto convertToImageDto(Image image) {
        if (image == null) {
            return null;
        }

        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setImage(image.getImage());

        return imageDto;
    }

    public List<ImageDto> convertToImageDtoList(List<Image> imageList) {
        if (imageList == null) {
            return null;
        }
        return convertToList(imageList, this::convertToImageDto);
    }
}
