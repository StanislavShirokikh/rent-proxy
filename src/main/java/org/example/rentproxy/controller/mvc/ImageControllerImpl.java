package org.example.rentproxy.controller.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rentproxy.dto.PostImageDto;
import org.example.rentproxy.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ImageControllerImpl implements ImageController {
    private final ImageService imageService;

    @Override
    public String getImagesByPostId(Model model, long id) {
        PostImageDto postImageDto = imageService.getImageByPostId(id);
        List<byte[]> imageBytes = postImageDto.getImages().stream()
                .map(imageDto -> imageDto.getImage().getData())
                .toList();

        model.addAttribute("images", encodeImageToBase64(imageBytes));

        return "post";
    }

    private List<String> encodeImageToBase64(List<byte[]> bytes) {
        return bytes.stream()
                .map(imageBytes -> Base64.getEncoder().encodeToString(imageBytes))
                .toList();
    }
}
