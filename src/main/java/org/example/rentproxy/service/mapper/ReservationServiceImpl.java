package org.example.rentproxy.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.ArchiveDto;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.repository.ArchiveRepository;
import org.example.rentproxy.repository.PostJpaRepository;
import org.example.rentproxy.repository.ReservationRequestRepository;
import org.example.rentproxy.repository.UserRepository;
import org.example.rentproxy.repository.entities.ReservationRequest;
import org.example.rentproxy.service.ReservationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRequestRepository reservationRequestRepository;
    private final ArchiveRepository archiveRepository;
    private final UserRepository userRepository;
    private final PostJpaRepository postJpaRepository;

    @Override
    public ReservationRequestDto createReservationRequest(ReservationRequestDto reservationRequestDto) {

        return null;
    }

    @Override
    public void deleteReservationRequestById(Long id) {

    }

    @Override
    public ReservationRequestDto getReservationRequestById(Long id) {
        return null;
    }

    @Override
    public ArchiveDto addToArchive(ReservationRequestDto reservationRequestDto) {
        return null;
    }

    @Override
    public void deleteFromArchiveById(Long id) {

    }

    @Override
    public ArchiveDto getFromArchiveById(Long id) {
        return null;
    }


}
