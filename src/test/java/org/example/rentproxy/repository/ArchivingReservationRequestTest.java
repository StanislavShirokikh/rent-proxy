package org.example.rentproxy.repository;

import org.example.rentproxy.repository.entities.ApartmentInfo;
import org.example.rentproxy.repository.entities.Appliance;
import org.example.rentproxy.repository.entities.BalconyType;
import org.example.rentproxy.repository.entities.BathroomType;
import org.example.rentproxy.repository.entities.Furniture;
import org.example.rentproxy.repository.entities.HouseInfo;
import org.example.rentproxy.repository.entities.HouseType;
import org.example.rentproxy.repository.entities.Post;
import org.example.rentproxy.repository.entities.RentConditionInfo;
import org.example.rentproxy.repository.entities.RentType;
import org.example.rentproxy.repository.entities.RepairType;
import org.example.rentproxy.repository.entities.ReservationRequest;
import org.example.rentproxy.repository.entities.RoomsType;
import org.example.rentproxy.repository.entities.TypeOfPayment;
import org.example.rentproxy.repository.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
public class ArchivingReservationRequestTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReservationRequestRepository reservationRequestRepository;

    @Test
    void archiveReservationRequest() {
        User renter = new User();
        renter.setFirstName("Имя");
        renter.setSecondName("Имя");
        renter.setLastName("Имя");
        renter.setLogin("renterArchiveReservationRequestLogin");
        renter.setPassword("renterArchiveConfirmReservationRequestPassword");

        User savedRenter = userRepository.save(renter);

        Set<TypeOfPayment> typeOfPaymentSet = new HashSet<>();
        TypeOfPayment typeOfPayment = new TypeOfPayment();
        typeOfPayment.setName("Включены в платёж");
        typeOfPaymentSet.add(typeOfPayment);

        RentConditionInfo rentConditionInfo = new RentConditionInfo();
        rentConditionInfo.setDeposit(30000.0);
        rentConditionInfo.setCommissionPercent(50);
        rentConditionInfo.setPrice(60000.0);
        rentConditionInfo.setCurrency("RUB");
        rentConditionInfo.setTypeOfPayment(typeOfPaymentSet);

        ApartmentInfo apartmentInfo = new ApartmentInfo();

        BathroomType bathroomType = new BathroomType();
        bathroomType.setName("Раздельный");
        apartmentInfo.setBathroomType(bathroomType);

        RepairType repairType = new RepairType();
        repairType.setName("Евро");
        apartmentInfo.setRepairType(repairType);

        BalconyType balconyType = new BalconyType();
        balconyType.setName("Балкон");
        apartmentInfo.setBalconyType(balconyType);
        RoomsType roomsType = new RoomsType();
        roomsType.setName("Изолированные");
        apartmentInfo.setRoomsType(roomsType);
        apartmentInfo.setRoomsCount(3);
        apartmentInfo.setTotalArea(74.1);
        apartmentInfo.setKitchenArea(9.7);
        apartmentInfo.setLivingSpace(51.3);
        apartmentInfo.setFlour(9);
        apartmentInfo.setAdditionally("Гардеробная");

        Set<Furniture> furnitures = new HashSet<>();
        Furniture furniture1 = new Furniture();
        furniture1.setName("Кухня");
        furnitures.add(furniture1);
        apartmentInfo.setFurniture(furnitures);

        Set<Appliance> appliances = new HashSet<>();
        Appliance appliance1 = new Appliance();
        appliance1.setName("Холодильник");
        Appliance appliance2 = new Appliance();
        appliance1.setName("Плита");
        Appliance appliance3 = new Appliance();
        appliance1.setName("Стиральная машина");
        Appliance appliance4 = new Appliance();
        appliance1.setName("Посудомойка");
        appliances.add(appliance1);
        appliances.add(appliance2);
        appliances.add(appliance3);
        appliances.add(appliance4);
        apartmentInfo.setAppliance(appliances);

        HouseInfo houseInfo = new HouseInfo();
        HouseType houseType = new HouseType();
        houseType.setName("Кирпичный");
        houseInfo.setHouseType(houseType);
        houseInfo.setAddress("Большевистская 143");
        houseInfo.setFloursCount(26);

        User landlord = new User();
        landlord.setFirstName("Имя");
        landlord.setSecondName("Имя");
        landlord.setLastName("Имя");
        landlord.setLogin("landlordArchiveReservationRequestLogin");
        landlord.setPassword("landlordArchiveReservationRequestPassword");

        Post post = new Post();
        post.setUser(userRepository.save(landlord));
        post.setRentConditionInfo(rentConditionInfo);
        post.setApartmentInfo(apartmentInfo);
        post.setHouseInfo(houseInfo);
        RentType rentType = new RentType();
        rentType.setName("Посуточная");
        post.setRentType(rentType);
        post.setName("Сдам 3-х комнатную квартиру");
        post.setTitle("Уютная квартира в тихом месте, метро 10 мин, все необходимое в квартире имеется");
        post.setDate(LocalDate.now());

        rentConditionInfo.setPost(post);
        apartmentInfo.setPost(post);
        houseInfo.setPost(post);

        Post savedPost = postRepository.save(post);

        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setPost(savedPost);
        reservationRequest.setUser(savedRenter);
        reservationRequest.setConfirmed(true);
        reservationRequest.setArchived(false);
        reservationRequest.setDate(LocalDate.now());

        ReservationRequest savedReservationRequest = reservationRequestRepository.save(reservationRequest);
        reservationRequestRepository.archiveReservationRequest(savedReservationRequest.getId());

        ReservationRequest archivedReservationRequest = reservationRequestRepository.findReservationRequestById(
                savedReservationRequest.getId());

        assertEquals(savedReservationRequest.getId(), archivedReservationRequest.getId());
        assertEquals(savedReservationRequest.getPost().getId(), archivedReservationRequest.getPost().getId());
        assertEquals(
                savedReservationRequest.getUser().getId(),
                archivedReservationRequest.getUser().getId()
        );
        assertEquals(
                savedReservationRequest.getPost().getUser().getId(),
                archivedReservationRequest.getPost().getUser().getId()
        );
        assertTrue(archivedReservationRequest.getConfirmed());
        assertTrue(archivedReservationRequest.getArchived());
        assertEquals(savedReservationRequest.getDate(), archivedReservationRequest.getDate());
    }
}
