package org.example.rentproxy.service;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.ArchiveDto;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.repository.ArchiveRepository;
import org.example.rentproxy.repository.PostJpaRepository;
import org.example.rentproxy.repository.ReservationRequestRepository;
import org.example.rentproxy.repository.UserRepository;
import org.example.rentproxy.repository.entities.ReservationRequest;
import org.example.rentproxy.service.mapper.DtoMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRequestRepository reservationRequestRepository;
    private final ArchiveRepository archiveRepository;
    private final UserRepository userRepository;
    private final PostJpaRepository postJpaRepository;
    private final DtoMapper dtoMapper;

    @Override
    public ReservationRequestDto createReservationRequest(ReservationRequestDto reservationRequestDto) {
        if (!reservationRequestRepository.existsByPostId(reservationRequestDto.getPostDto().getId())) {
            ReservationRequest reservationRequest = dtoMapper.mapToReservationRequest(reservationRequestDto);
            reservationRequest.setUser(userRepository.findByLogin(reservationRequestDto.getUserDto().getLogin()));

            return dtoMapper.mapToReservationRequestDto(reservationRequestRepository.save(reservationRequest));
        }
        return null;
    }

    @Override
    public void deleteReservationRequestById(Long id) {
        if (reservationRequestRepository.existsById(id)) {
            reservationRequestRepository.deleteById(id);
        }
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    @Override
    public void deleteOldReservationRequest() {
        List<ReservationRequest> reservationRequests = reservationRequestRepository.
                findReservationRequestByDateBefore(LocalDate.now().minusDays(1));
        if (reservationRequests != null) {
            reservationRequestRepository.deleteAll(reservationRequests);
        }
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
