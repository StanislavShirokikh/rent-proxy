package org.example.rentproxy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.MessageDto;
import org.example.rentproxy.exception.ChatDisabledException;
import org.example.rentproxy.mapper.MessageDtoMapper;
import org.example.rentproxy.repository.jpa.*;
import org.example.rentproxy.repository.jpa.entities.Dialog;
import org.example.rentproxy.repository.jpa.entities.Message;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DialogMessageServiceImpl implements DialogMessageService {
    private final DialogRepository dialogRepository;
    private final ReservationRequestRepository reservationRequestRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final MessageDtoMapper messageDtoMapper;

    @Transactional
    public MessageDto sendMessage(String username, long postId, String text) {
        if (reservationRequestRepository.findArchivedByPostId(postId) > 0) {
            throw new ChatDisabledException();
        }
        Dialog dialog = getDialog(username, postId);

        Message message = new Message();
        message.setMessageText(text);
        message.setDialog(dialog);

        return messageDtoMapper.convertToMessageDto(messageRepository.save(message));
    }

    private Dialog getDialog(String senderName, long postId) {
        Dialog dialog = dialogRepository.findDialogBySenderLoginAndPostId(senderName, postId);

        if (dialog != null) {
            return dialog;
        }
        dialog = new Dialog();
        dialog.setPostId(postId);
        dialog.setSender(userRepository.findByLogin(senderName));
        dialog.setReceiver(userRepository.findByPostId(postId));

        return dialogRepository.save(dialog);
    }
}
