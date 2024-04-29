package org.example.rentproxy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.exception.ReservationRequestException;
import org.example.rentproxy.mapper.ReservationRequestDtoMapper;
import org.example.rentproxy.mapper.ReservationRequestMapper;
import org.example.rentproxy.repository.ReservationRequestRepository;
import org.example.rentproxy.repository.UserRepository;
import org.example.rentproxy.repository.entities.ReservationRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRequestRepository reservationRequestRepository;
    private final UserRepository userRepository;
    private final ReservationRequestDtoMapper reservationRequestDtoMapper;
    private final ReservationRequestMapper reservationRequestMapper;
    private final ReservationRequestProperties reservationRequestProperties;

    @Override
    public ReservationRequestDto createReservationRequest(ReservationRequestDto reservationRequestDto) throws ReservationRequestException {
        if (reservationRequestRepository.existsByPostId(reservationRequestDto.getPostDto().getId())) {
            throw new ReservationRequestException();
        }

        ReservationRequest reservationRequest = reservationRequestMapper.convertToReservationRequest(reservationRequestDto);
        reservationRequest.setUser(userRepository.findByLogin(reservationRequestDto.getUserDto().getLogin()));

        return reservationRequestDtoMapper.convertToReservationRequestDto(reservationRequestRepository.save(reservationRequest));
    }

    @Override
    public void deleteReservationRequestById(Long id) {
        reservationRequestRepository.deleteById(id);
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    @Override
    public void deleteOutdatedReservationRequest() {
        reservationRequestRepository.deleteOutdatedReservationRequest(
                LocalDate.now().minusDays(reservationRequestProperties.getOutdatedPeriod())
        );
    }

    @Override
    public ReservationRequestDto getReservationRequestById(Long id) {
        return reservationRequestDtoMapper.convertToReservationRequestDto(reservationRequestRepository.findReservationRequestById(id));
    }

    @Override
    public ReservationRequestDto confirmReservationRequest(ReservationRequestDto reservationRequestDto) throws ReservationRequestException {
        if (!reservationRequestRepository.existsReservationRequestById(reservationRequestDto.getId())) {
            throw new ReservationRequestException();
        }

        return reservationRequestDtoMapper.convertToReservationRequestDto(reservationRequestRepository.
                confirmReservationRequest(reservationRequestDto.getId(), reservationRequestDto.getConfirmed()));
    }

    @Override
    public ReservationRequestDto archiveReservationRequest(ReservationRequestDto reservationRequestDto) {
        return reservationRequestDtoMapper.convertToReservationRequestDto(reservationRequestRepository.
                archiveReservationRequest(reservationRequestDto.getId(), reservationRequestDto.getConfirmed()));
    }

    @Override
    public List<ReservationRequestDto> getSentReservationsByUsername(String username) {
        return reservationRequestDtoMapper.convertToListReservationRequestDto(
                reservationRequestRepository.getSentReservationsByUsername(username)
        );
    }

    @Override
    public List<ReservationRequestDto> getReceivedReservationRequestsByUsername(String username) {
        return reservationRequestDtoMapper.convertToListReservationRequestDto(
                reservationRequestRepository.getReceivedReservationRequestsByUsername(username)
        );
    }

    @Override
    public List<ReservationRequestDto> getArchivedReservationRequestsByUsername(String username) {
        return reservationRequestDtoMapper.convertToListReservationRequestDto(
                reservationRequestRepository.getArchivedReservationRequestsByUsername(username)
        );
    }
}
