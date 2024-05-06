package org.example.rentproxy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.exception.ReservationRequestBadRequestException;
import org.example.rentproxy.exception.ReservationRequestNotFoundException;
import org.example.rentproxy.mapper.ReservationRequestDtoMapper;
import org.example.rentproxy.mapper.ReservationRequestMapper;
import org.example.rentproxy.repository.PostRepository;
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
    private final PostRepository postRepository;
    private final ReservationRequestDtoMapper reservationRequestDtoMapper;
    private final ReservationRequestMapper reservationRequestMapper;
    private final ReservationRequestProperties reservationRequestProperties;

    @Override
    public ReservationRequestDto createReservationRequest(ReservationRequestDto reservationRequestDto) throws ReservationRequestBadRequestException {
        if (reservationRequestRepository.existsByUserLoginAndPostId(reservationRequestDto.getUserDto().getLogin(),
                reservationRequestDto.getPostDto().getId())) {
            throw new ReservationRequestBadRequestException();
        }

        ReservationRequest reservationRequest = reservationRequestMapper.convertToReservationRequest(reservationRequestDto);
        reservationRequest.setUser(userRepository.findByLogin(reservationRequestDto.getUserDto().getLogin()));
        reservationRequest.setPost(postRepository.findPostById(reservationRequestDto.getPostDto().getId()));

        return reservationRequestDtoMapper.convertToReservationRequestDto(reservationRequestRepository.save(reservationRequest));
    }

    @Override
    public void deleteReservationRequestById(long id) {
        reservationRequestRepository.deleteById(id);
    }

    @Scheduled(fixedDelayString = "${rent-proxy.reservation.request.fix-delay}" )
    @Override
    public void deleteOutdatedReservationRequest() {
        int countDeletedReservations = reservationRequestRepository.deleteOutdatedReservationRequest(
                LocalDate.now().minusDays(reservationRequestProperties.getOutdatedPeriod())
        );
        log.info("Deleted {} reservation requests", countDeletedReservations);
    }

    @Override
    public ReservationRequestDto getReservationRequestById(long id) {
        return reservationRequestDtoMapper.convertToReservationRequestDto(reservationRequestRepository.findReservationRequestById(id));
    }

    @Override
    public ReservationRequestDto confirmReservationRequest(long id) throws ReservationRequestNotFoundException {
        validateRequestExisting(id);
        reservationRequestRepository.confirmReservationRequest(id);

        return reservationRequestDtoMapper.convertToReservationRequestDto(reservationRequestRepository.findReservationRequestById(id));
    }

    @Override
    public ReservationRequestDto archiveReservationRequest(long id) throws ReservationRequestNotFoundException {
        validateRequestExisting(id);
        reservationRequestRepository.archiveReservationRequest(id);

        return reservationRequestDtoMapper.convertToReservationRequestDto(reservationRequestRepository.findReservationRequestById(id));
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

    @Override
    public List<ReservationRequestDto> getArchivedReservationRequestsByPostUsername(String username) {
        return reservationRequestDtoMapper.convertToListReservationRequestDto(
                reservationRequestRepository.getArchivedReservationRequestsByPostUsername(username)
        );
    }

    private void validateRequestExisting(long id) throws ReservationRequestBadRequestException {
        if (!reservationRequestRepository.existsReservationRequestById(id)) {
            throw new ReservationRequestNotFoundException();
        }
    }
}
