package org.example.rentproxy.repository.mongo.documents;

import lombok.Data;
import org.bson.types.Binary;

@Data
public class Image {
    private String id;
    private Binary image;
}
