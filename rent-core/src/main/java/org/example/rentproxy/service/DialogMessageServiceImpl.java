package org.example.rentproxy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.DialogDto;
import org.example.rentproxy.exception.ChatDisabledException;
import org.example.rentproxy.mapper.DialogDtoMapper;
import org.example.rentproxy.mapper.DialogMapper;
import org.example.rentproxy.repository.jpa.DialogRepository;
import org.example.rentproxy.repository.jpa.ReservationRequestRepository;
import org.example.rentproxy.repository.jpa.entities.Dialog;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DialogMessageServiceImpl implements DialogMessageService {
    private final DialogMapper dialogMapper;
    private final DialogDtoMapper dialogDtoMapper;
    private final DialogRepository dialogRepository;
    private final ReservationRequestRepository reservationRequestRepository;

    @Transactional
    public DialogDto createDialog(DialogDto dialogDto) {
        if (reservationRequestRepository.findArchivedByPostId(dialogDto.getPostId()) > 0) {
            throw new ChatDisabledException();
        }

        Dialog dialog = getDialog(dialogDto);


        return null;
    }

    private Dialog getDialog(DialogDto dialogDto) {
        Dialog dialog = dialogRepository.getDialogBySenderNameAndPostId(
                dialogDto.getSender().getLogin(),
                dialogDto.getPostId()
                );

        if (dialog == null) {
            dialog = dialogMapper.convertToDialogDialog(dialogDto);
        }
        return dialog;
    }
}
