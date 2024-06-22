package org.example.rentproxy.dto;

import lombok.Data;
import org.bson.types.Binary;
@Data
public class ImageDto {
    private String id;
    private Binary image;
}
