package org.example.rentproxy.controller.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.example.rentproxy.dto.PostImageDto;

import java.io.IOException;
import java.util.List;

public interface ImageLoaderController {
    @PostMapping("/uploadImage")
    PostImageDto uploadImage(@RequestParam("postId") long postId,
                             @RequestParam("files") List<MultipartFile> files) throws IOException;
}
