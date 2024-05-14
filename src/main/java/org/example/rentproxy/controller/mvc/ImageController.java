package org.example.rentproxy.controller.mvc;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mvc")
public interface ImageController {
    @GetMapping("/post/{id}")
    String getImagesByPostId(Model model, @PathVariable("id") long id);
}
