package org.example.rentproxy.repository;

import org.example.rentproxy.repository.jpa.PostRepository;
import org.example.rentproxy.repository.jpa.UserRepository;
import org.example.rentproxy.repository.jpa.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class GetEntityTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findPostById() {
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

        Set<Furniture> furniture = new HashSet<>();
        Furniture furniture1 = new Furniture();
        furniture1.setName("Кухня");
        Furniture furniture2 = new Furniture();
        furniture2.setName("Хранение одежды");
        furniture.add(furniture1);
        furniture.add(furniture2);
        apartmentInfo.setFurniture(furniture);

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

        Post savedPost = postRepository.save(post);

        Post actualPost = postRepository.findPostById(savedPost.getId());

        assertNotNull(actualPost.getId());
        assertNotNull(actualPost.getUser().getId());
        assertEquals(savedPost.getUser().getFirstName(), actualPost.getUser().getFirstName());
        assertEquals(savedPost.getUser().getSecondName(), actualPost.getUser().getSecondName());
        assertEquals(savedPost.getUser().getLastName(), actualPost.getUser().getLastName());
        assertEquals(savedPost.getUser().getLogin(), actualPost.getUser().getLogin());
        assertEquals(savedPost.getUser().getPassword(), actualPost.getUser().getPassword());

        assertNotNull(actualPost.getRentConditionInfo().getId());
        assertNotNull(actualPost.getRentConditionInfo().getPost().getId());
        assertEquals(savedPost.getRentConditionInfo().getDeposit(), actualPost.getRentConditionInfo().getDeposit());
        assertEquals(savedPost.getRentConditionInfo().getCommissionPercent(), actualPost.getRentConditionInfo().getCommissionPercent());
        assertEquals(savedPost.getRentConditionInfo().getCurrency(), actualPost.getRentConditionInfo().getCurrency());
        assertEquals(savedPost.getRentConditionInfo().getTypeOfPayment(), actualPost.getRentConditionInfo().getTypeOfPayment());

        assertNotNull(actualPost.getApartmentInfo().getId());
        assertNotNull(actualPost.getApartmentInfo().getPost().getId());
        assertNotNull(actualPost.getApartmentInfo().getBathroomType().getId());
        assertEquals(savedPost.getApartmentInfo().getBathroomType().getName(), actualPost.getApartmentInfo().getBathroomType().getName());
        assertNotNull(actualPost.getApartmentInfo().getRepairType().getId());
        assertEquals(savedPost.getApartmentInfo().getRepairType().getName(), actualPost.getApartmentInfo().getRepairType().getName());
        assertNotNull(actualPost.getApartmentInfo().getBalconyType().getId());
        assertEquals(savedPost.getApartmentInfo().getBalconyType().getName(), actualPost.getApartmentInfo().getBalconyType().getName());
        assertNotNull(actualPost.getApartmentInfo().getRoomsType().getId());
        assertEquals(savedPost.getApartmentInfo().getRoomsType().getName(), actualPost.getApartmentInfo().getRoomsType().getName());
        assertEquals(savedPost.getApartmentInfo().getRoomsCount(), actualPost.getApartmentInfo().getRoomsCount());
        assertEquals(savedPost.getApartmentInfo().getTotalArea(), actualPost.getApartmentInfo().getTotalArea());
        assertEquals(savedPost.getApartmentInfo().getKitchenArea(), actualPost.getApartmentInfo().getKitchenArea());
        assertEquals(savedPost.getApartmentInfo().getLivingSpace(), actualPost.getApartmentInfo().getLivingSpace());
        assertEquals(savedPost.getApartmentInfo().getFlour(), actualPost.getApartmentInfo().getFlour());
        assertEquals(savedPost.getApartmentInfo().getAdditionally(), actualPost.getApartmentInfo().getAdditionally());
        assertEquals(savedPost.getApartmentInfo().getFurniture(), actualPost.getApartmentInfo().getFurniture());
        assertEquals(savedPost.getApartmentInfo().getAppliance(), actualPost.getApartmentInfo().getAppliance());

        assertNotNull(actualPost.getHouseInfo().getId());
        assertNotNull(actualPost.getHouseInfo().getPost().getId());
        assertNotNull(actualPost.getHouseInfo().getHouseType().getId());
        assertEquals(savedPost.getHouseInfo().getHouseType().getName(), actualPost.getHouseInfo().getHouseType().getName());
        assertEquals(savedPost.getHouseInfo().getAddress(), actualPost.getHouseInfo().getAddress());
        assertEquals(savedPost.getHouseInfo().getFloursCount(), actualPost.getHouseInfo().getFloursCount());

        assertNotNull(actualPost.getRentType().getId());
        assertEquals(savedPost.getRentType().getName(), actualPost.getRentType().getName());

        assertEquals(savedPost.getName(), actualPost.getName());
        assertEquals(savedPost.getTitle(), actualPost.getTitle());
        assertEquals(savedPost.getDate(), actualPost.getDate());
    }
}
