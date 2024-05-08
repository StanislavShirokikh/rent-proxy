package org.example.rentproxy.repository.mongo.documents;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Data
public class Image {
    @MongoId
    private String id;
    private Binary image;
}
