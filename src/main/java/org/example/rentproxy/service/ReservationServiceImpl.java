package org.example.rentproxy.service;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.example.rentproxy.dto.ArchiveDto;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.exception.ReservationRequestException;
import org.example.rentproxy.repository.ArchiveRepository;
import org.example.rentproxy.repository.PostJpaRepository;
import org.example.rentproxy.repository.ReservationRequestRepository;
import org.example.rentproxy.repository.UserRepository;
import org.example.rentproxy.repository.entities.Archive;
import org.example.rentproxy.repository.entities.ReservationRequest;
import org.example.rentproxy.service.mapper.DtoMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRequestRepository reservationRequestRepository;
    private final ArchiveRepository archiveRepository;
    private final UserRepository userRepository;
    private final PostJpaRepository postJpaRepository;
    private final DtoMapper dtoMapper;

    @Override
    public ReservationRequestDto createReservationRequest(ReservationRequestDto reservationRequestDto) throws ReservationRequestException {
        if (reservationRequestRepository.existsByPostId(reservationRequestDto.getPostDto().getId())) {
            throw new ReservationRequestException();
        }

        ReservationRequest reservationRequest = dtoMapper.convertToReservationRequest(reservationRequestDto);
        reservationRequest.setUser(userRepository.findByLogin(reservationRequestDto.getUserDto().getLogin()));

        return dtoMapper.convertToReservationRequestDto(reservationRequestRepository.save(reservationRequest));
    }

    @Override
    public void deleteReservationRequestById(Long id) {
        reservationRequestRepository.deleteById(id);
    }

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    @Override
    public void deleteOutdatedReservationRequest(Long outdatedPeriod) {

        reservationRequestRepository.deleteOutdatedReservationRequest(LocalDate.now().minusDays());
    }

    @Override
    public ReservationRequestDto getReservationRequestById(Long id) {
        return dtoMapper.convertToReservationRequestDto(reservationRequestRepository.findReservationRequestById(id));
    }

    @Override
    public ArchiveDto addToArchive(ArchiveDto archiveDto) {
        Archive archive = dtoMapper.convertToArchive(archiveDto);
        archive.setReservationRequest(reservationRequestRepository.findReservationRequestById(
                archiveDto.getReservationRequestDto().getId()));

        Archive savedArchive = archiveRepository.save(archive);

        postJpaRepository.deleteById(reservationRequestRepository.
                getPostIdById(savedArchive.getReservationRequest().getId()));

        return dtoMapper.convertToArchiveDto(savedArchive);
    }

    @Override
    public void deleteFromArchiveById(Long id) {
        if (archiveRepository.existsById(id)) {
            archiveRepository.deleteById(id);
        }
    }

    @Override
    public ArchiveDto getFromArchiveById(Long id) {
        return dtoMapper.convertToArchiveDto(archiveRepository.findArchiveById(id));
    }
}
