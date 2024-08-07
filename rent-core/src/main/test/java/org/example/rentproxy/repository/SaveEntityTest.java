package org.example.rentproxy.repository;

import org.example.rentproxy.repository.jpa.PostRepository;
import org.example.rentproxy.repository.jpa.UserRepository;
import org.example.rentproxy.repository.jpa.entities.ApartmentInfo;
import org.example.rentproxy.repository.jpa.entities.Appliance;
import org.example.rentproxy.repository.jpa.entities.BalconyType;
import org.example.rentproxy.repository.jpa.entities.BathroomType;
import org.example.rentproxy.repository.jpa.entities.Furniture;
import org.example.rentproxy.repository.jpa.entities.HouseInfo;
import org.example.rentproxy.repository.jpa.entities.HouseType;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.RentConditionInfo;
import org.example.rentproxy.repository.jpa.entities.RentType;
import org.example.rentproxy.repository.jpa.entities.RepairType;
import org.example.rentproxy.repository.jpa.entities.RoomsType;
import org.example.rentproxy.repository.jpa.entities.TypeOfPayment;
import org.example.rentproxy.repository.jpa.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SaveEntityTest {
    @Autowired
    private PostRepository postJpaRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void savedPost() {
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

        User user = new User();
        user.setFirstName("Петр");
        user.setSecondName("Иванович");
        user.setLastName("Иванов");
        user.setLogin("IvanLogin" + UUID.randomUUID());
        user.setPassword("1234");

        Post post = new Post();
        post.setUser(userRepository.save(user));
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

        Post savedPost = postJpaRepository.save(post);
        Assertions.assertNotNull(savedPost.getId());
        Assertions.assertNotNull(savedPost.getUser().getId());
        assertEquals(user.getFirstName(), savedPost.getUser().getFirstName());
        assertEquals(user.getSecondName(), savedPost.getUser().getSecondName());
        assertEquals(user.getLastName(), savedPost.getUser().getLastName());
        assertEquals(user.getLogin(), savedPost.getUser().getLogin());
        assertEquals(user.getPassword(), savedPost.getUser().getPassword());

        Assertions.assertNotNull(savedPost.getRentConditionInfo().getId());
        Assertions.assertNotNull(savedPost.getRentConditionInfo().getPost().getId());
        assertEquals(rentConditionInfo.getDeposit(), savedPost.getRentConditionInfo().getDeposit());
        assertEquals(rentConditionInfo.getCommissionPercent(), savedPost.getRentConditionInfo().getCommissionPercent());
        assertEquals(rentConditionInfo.getCurrency(), savedPost.getRentConditionInfo().getCurrency());
        assertEquals(rentConditionInfo.getTypeOfPayment(), savedPost.getRentConditionInfo().getTypeOfPayment());

        Assertions.assertNotNull(savedPost.getApartmentInfo().getId());
        Assertions.assertNotNull(savedPost.getApartmentInfo().getPost().getId());
        Assertions.assertNotNull(savedPost.getApartmentInfo().getBathroomType().getId());
        assertEquals(apartmentInfo.getBathroomType().getName(), savedPost.getApartmentInfo().getBathroomType().getName());
        Assertions.assertNotNull(savedPost.getApartmentInfo().getRepairType().getId());
        assertEquals(apartmentInfo.getRepairType().getName(), savedPost.getApartmentInfo().getRepairType().getName());
        Assertions.assertNotNull(savedPost.getApartmentInfo().getBalconyType().getId());
        assertEquals(apartmentInfo.getBalconyType().getName(), savedPost.getApartmentInfo().getBalconyType().getName());
        Assertions.assertNotNull(savedPost.getApartmentInfo().getRoomsType().getId());
        assertEquals(apartmentInfo.getRoomsType().getName(), savedPost.getApartmentInfo().getRoomsType().getName());
        assertEquals(apartmentInfo.getRoomsCount(), savedPost.getApartmentInfo().getRoomsCount());
        assertEquals(apartmentInfo.getTotalArea(), savedPost.getApartmentInfo().getTotalArea());
        assertEquals(apartmentInfo.getKitchenArea(), savedPost.getApartmentInfo().getKitchenArea());
        assertEquals(apartmentInfo.getLivingSpace(), savedPost.getApartmentInfo().getLivingSpace());
        assertEquals(apartmentInfo.getFlour(), savedPost.getApartmentInfo().getFlour());
        assertEquals(apartmentInfo.getAdditionally(), savedPost.getApartmentInfo().getAdditionally());
        assertEquals(apartmentInfo.getFurniture(), savedPost.getApartmentInfo().getFurniture());
        assertEquals(apartmentInfo.getAppliance(), savedPost.getApartmentInfo().getAppliance());

        Assertions.assertNotNull(savedPost.getHouseInfo().getId());
        Assertions.assertNotNull(savedPost.getHouseInfo().getPost().getId());
        Assertions.assertNotNull(savedPost.getHouseInfo().getHouseType().getId());
        assertEquals(houseInfo.getHouseType().getName(), savedPost.getHouseInfo().getHouseType().getName());
        assertEquals(houseInfo.getAddress(), savedPost.getHouseInfo().getAddress());
        assertEquals(houseInfo.getFloursCount(), savedPost.getHouseInfo().getFloursCount());

        Assertions.assertNotNull(savedPost.getRentType().getId());
        assertEquals(rentType.getName(), savedPost.getRentType().getName());

        assertEquals(post.getName(), savedPost.getName());
        assertEquals(post.getTitle(), savedPost.getTitle());
        assertEquals(post.getDate(), savedPost.getDate());
    }
}