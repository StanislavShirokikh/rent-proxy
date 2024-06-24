package org.example.rentproxy.request;

import lombok.Data;

@Data
public class SendMessageRequest {
    private Long postId;
    private String text;
}
