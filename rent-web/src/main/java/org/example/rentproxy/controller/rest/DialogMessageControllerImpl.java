package org.example.rentproxy.controller.rest;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.service.DialogMessageService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DialogMessageControllerImpl implements DialogMessageController{
    private final DialogMessageService dialogMessageService;

    public DialogDto createDialog(UserDetails userDetails, DialogDto dialogDto) {

        return null;
    }
}
