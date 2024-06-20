package org.example.rentproxy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.dto.MessageDto;
import org.example.rentproxy.exception.ChatDisabledException;
import org.example.rentproxy.mapper.MessageDtoMapper;
import org.example.rentproxy.mapper.MessageMapper;
import org.example.rentproxy.repository.jpa.*;
import org.example.rentproxy.repository.jpa.entities.Dialog;
import org.example.rentproxy.repository.jpa.entities.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DialogMessageServiceImpl implements DialogMessageService {
    private final MessageMapper messageMapper;
    private final MessageDtoMapper messageDtoMapper;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final MessageRepository messageRepository;
    private final DialogRepository dialogRepository;
    private final ReservationRequestRepository reservationRequestRepository;

    @Transactional
    public DialogDto createDialogAndSendMessage(MessageDto messageDto) {
        if (reservationRequestRepository.findArchivedByPostId(messageDto.getPostId()) > 0) {
            throw new ChatDisabledException();
        }
        Dialog dialog = createDialogIfNeeded(messageDto.getSenderName(), messageDto.getPostId(), messageDto.getCreationDateTime());
        Message message = messageMapper.convertToMessage(messageDto);
        message.setSender(userRepository.findByLogin(messageDto.getSenderName()));
        message.setReceiver(userRepository.findUserByPostId(messageDto.getPostId()));
        List<Message> messages = new ArrayList<>();
        messages.add(message);
        dialog.setMessages(messages);

        return null;
    }

    private Dialog createDialogIfNeeded(String senderName, Long postId, LocalDateTime creationDateTime) {
        Dialog dialog = dialogRepository.getDialogBySenderNameAndPostId(senderName, postId);
        if (dialog == null) {
            dialog = new Dialog();
            dialog.setCreationDateTime(creationDateTime);
        }
        return dialog;
    }
}
