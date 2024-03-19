package org.example.rentproxy.repository;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.entities.Post;
import org.example.rentproxy.entities.TypeOfPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{
    private final PostJpaRepository postJpaRepository;
    private final ApplianceRepository applianceRepository;
    private final BalconyTypeRepository balconyTypeRepository;
    private final BathroomTypeRepository bathroomTypeRepository;
    private final FurnitureRepository furnitureRepository;
    private final HouseTypeRepository houseTypeRepository;
    private final RentTypeRepository rentTypeRepository;
    private final RepairTypeRepository repairTypeRepository;
    private final RoomsTypeRepository roomsTypeRepository;
    private final TypeOfPaymentRepository typeOfPaymentRepository;


    @Override
    public Post save(Post post) {
        Set<TypeOfPayment> fetchedTypes = typeOfPaymentRepository.findByNameIn(
                post.getRentConditionInfo().getTypeOfPayment().stream()
                        .map(TypeOfPayment::getName)
                        .collect(Collectors.toSet())
        ); //todo посмотреть какой запрос кинул хибернейт
        return null;
    }
}
