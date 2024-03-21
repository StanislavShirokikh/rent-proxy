package org.example.rentproxy.repository;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.entities.Appliance;
import org.example.rentproxy.entities.Furniture;
import org.example.rentproxy.entities.Post;
import org.example.rentproxy.entities.TypeOfPayment;
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
                        .collect(Collectors.toSet()));
        post.getRentConditionInfo().setTypeOfPayment(fetchedTypes);
        post.getApartmentInfo()
                .setBathroomType(bathroomTypeRepository
                        .findByName(post.getApartmentInfo()
                                .getBathroomType()
                                .getName()));

        post.getApartmentInfo()
                .setRepairType(repairTypeRepository
                        .findByName(post.getApartmentInfo()
                                .getRepairType()
                                .getName()));
        post.getApartmentInfo()
                .setBalconyType(balconyTypeRepository
                        .findByName(post.getApartmentInfo()
                                .getBalconyType()
                                .getName()));

        post.getApartmentInfo()
                .setRoomsType(roomsTypeRepository
                        .findByName(post.getApartmentInfo()
                                .getRoomsType()
                                .getName()));

        Set<Furniture> fetchedFurniture = furnitureRepository.findByNameIn(
                post.getApartmentInfo().getFurniture().stream()
                        .map(Furniture::getName)
                        .collect(Collectors.toSet()));
        post.getApartmentInfo().setFurniture(fetchedFurniture);

        Set<Appliance> fetchedAppliance = applianceRepository.findByNameIn(
                post.getApartmentInfo().getAppliance().stream()
                        .map(Appliance::getName)
                        .collect(Collectors.toSet()));
        post.getApartmentInfo().setAppliance(fetchedAppliance);

        post.getHouseInfo()
                .setHouseType(houseTypeRepository
                        .findByName(post.getHouseInfo()
                                .getHouseType()
                                .getName()));
        post.setRentType(rentTypeRepository.findByName(post.getRentType().getName()));

        return postJpaRepository.save(post);
    }
}
