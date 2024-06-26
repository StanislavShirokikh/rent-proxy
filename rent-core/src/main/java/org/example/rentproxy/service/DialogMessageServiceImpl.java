package org.example.rentproxy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.dto.MessageDto;
import org.example.rentproxy.exception.DialogClosureNotAllowedException;
import org.example.rentproxy.exception.DialogDisabledException;
import org.example.rentproxy.exception.ClosedDialogException;
import org.example.rentproxy.mapper.DialogDtoMapper;
import org.example.rentproxy.mapper.MessageDtoMapper;
import org.example.rentproxy.repository.jpa.*;
import org.example.rentproxy.repository.jpa.entities.Dialog;
import org.example.rentproxy.repository.jpa.entities.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DialogMessageServiceImpl implements DialogMessageService {
    private final DialogRepository dialogRepository;
    private final ReservationRequestRepository reservationRequestRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final MessageStatusRepository messageStatusRepository;
    private final MessageDtoMapper messageDtoMapper;
    private final DialogDtoMapper dialogDtoMapper;
    private final PostRepository postRepository;


    @Transactional
    public MessageDto sendMessage(String username, long postId, String text) {
        if (reservationRequestRepository.findArchivedByPostId(postId) > 0) {
            throw new DialogDisabledException();
        }
        Dialog dialog = getDialog(username, postId);

        if (dialog.getIsClosed()) {
            throw new ClosedDialogException();
        }

        Message message = new Message();
        message.setText(text);
        message.setStatus(messageStatusRepository.findByName(MessageStatus.UNREAD.getFieldName()));
        message.setDialog(dialog);

        return messageDtoMapper.convertToMessageDto(messageRepository.save(message));
    }

    @Override
    public List<MessageDto> getMessagesByReceiverUsername(String username, long pageSize, long pageNumber) {
        List<Message> messages = messageRepository.findMessagesByReceiverUsernameWithSortAndPagination(
                username,
                pageSize,
                pageNumber
        );
        return messageDtoMapper.convertToListMessageDto(messages);
    }

    @Override
    public List<MessageDto> findUnreadMessageByReceiverUsername(String username, long pageSize, long pageNumber) {
        List<Message> messages = messageRepository.findUnreadMessagesByReceiverUsername(username, pageSize, pageNumber);
        return messageDtoMapper.convertToListMessageDto(messages);
    }

    @Transactional
    @Override
    public DialogDto closeDialog(String username, long chatId) {
        Dialog dialog = dialogRepository.findDialogByPostUserLoginAndDialogId(chatId, username);
        if (dialog == null) {
            throw new DialogClosureNotAllowedException();
        }
        dialog.setIsClosed(true);
        return dialogDtoMapper.convertToDialogDto(dialogRepository.save(dialog));
    }

    private Dialog getDialog(String senderName, long postId) {
        Dialog dialog = dialogRepository.findDialogBySenderLoginAndPostId(senderName, postId);
        if (dialog != null) {
            return dialog;
        }
        dialog = new Dialog();
        dialog.setPost(postRepository.findPostById(postId));
        dialog.setSender(userRepository.findByLogin(senderName));
        dialog.setReceiver(userRepository.findByPostId(postId));
        dialog.setIsClosed(false);

        return dialogRepository.save(dialog);
    }
}
