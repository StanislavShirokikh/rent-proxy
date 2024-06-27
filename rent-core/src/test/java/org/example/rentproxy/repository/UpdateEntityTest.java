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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UpdateEntityTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void updatePostById() {
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
        Furniture furniture2 = new Furniture();
        furniture2.setName("Хранение одежды");
        furnitures.add(furniture2);
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
        user.setLogin("IvanLogin");
        user.setPassword("1234");

        Post post = new Post();
        post.setUser(userRepository.save(user));
        post.setRentConditionInfo(rentConditionInfo);
        post.setApartmentInfo(apartmentInfo);
        post.setHouseInfo(houseInfo);
        RentType rentType = new RentType();
        rentType.setName("Долгосрочная");
        post.setRentType(rentType);
        post.setName("Сдам 3-х комнатную квартиру");
        post.setTitle("Уютная квартира в тихом месте, метро 10 мин, все необходимое в квартире имеется");
        post.setDate(LocalDate.now());

        rentConditionInfo.setPost(post);
        apartmentInfo.setPost(post);
        houseInfo.setPost(post);

        Post savedPost = postRepository.save(post);

        Set<TypeOfPayment> updatedTypeOfPaymentSet = new HashSet<>();
        TypeOfPayment updatedTypeOfPayment = new TypeOfPayment();
        updatedTypeOfPayment.setName("Оплачиваются отдельно");
        updatedTypeOfPaymentSet.add(updatedTypeOfPayment);

        RentConditionInfo updatedRentConditionInfo = new RentConditionInfo();
        updatedRentConditionInfo.setDeposit(32000.0);
        updatedRentConditionInfo.setCommissionPercent(60);
        updatedRentConditionInfo.setPrice(63000.0);
        updatedRentConditionInfo.setTypeOfPayment(updatedTypeOfPaymentSet);

        ApartmentInfo updatedApartmentInfo = new ApartmentInfo();

        BathroomType updatedBathroomType = new BathroomType();
        updatedBathroomType.setName("Совмещенный");
        updatedApartmentInfo.setBathroomType(updatedBathroomType);

        RepairType updatedRepairType = new RepairType();
        updatedRepairType.setName("Косметический");
        updatedApartmentInfo.setRepairType(updatedRepairType);

        BalconyType updatedBalconyType = new BalconyType();
        updatedBalconyType.setName("Лоджия");
        updatedApartmentInfo.setBalconyType(updatedBalconyType);
        RoomsType updatedRoomsType = new RoomsType();
        updatedRoomsType.setName("Смежные");
        updatedApartmentInfo.setRoomsType(updatedRoomsType);
        updatedApartmentInfo.setRoomsCount(3);
        updatedApartmentInfo.setTotalArea(74.1);
        updatedApartmentInfo.setKitchenArea(9.7);
        updatedApartmentInfo.setLivingSpace(51.3);
        updatedApartmentInfo.setFlour(9);
        updatedApartmentInfo.setAdditionally("Кабельное телевидение");

        Set<Furniture> updatedFurniture = new HashSet<>();
        Furniture updatedFurniture1 = new Furniture();
        updatedFurniture1.setName("Хранение одежды");

        updatedFurniture.add(updatedFurniture1);
        updatedApartmentInfo.setFurniture(updatedFurniture);

        Set<Appliance> updatedAppliances = new HashSet<>();
        Appliance updatedAppliance1 = new Appliance();
        updatedAppliance1.setName("Холодильник");
        updatedAppliances.add(updatedAppliance1);

        updatedApartmentInfo.setAppliance(updatedAppliances);

        HouseInfo updatedHouseInfo = new HouseInfo();
        HouseType updatedHouseType = new HouseType();
        updatedHouseType.setName("Панельный");
        updatedHouseInfo.setHouseType(updatedHouseType);
        updatedHouseInfo.setAddress("Большевистская 1");
        updatedHouseInfo.setFloursCount(8);

        Post updatedPost = new Post();
        updatedPost.setId(savedPost.getId());
        updatedPost.setUser(user);
        updatedPost.setRentConditionInfo(rentConditionInfo);
        updatedPost.setApartmentInfo(apartmentInfo);
        updatedPost.setHouseInfo(houseInfo);
        RentType updatedRentType = new RentType();
        updatedRentType.setName("Посуточная");
        updatedPost.setRentType(updatedRentType);
        updatedPost.setName("Сдам 2-х комнатную квартиру");
        updatedPost.setTitle("метро 15 мин, все необходимое в квартире имеется");
        updatedPost.setDate(LocalDate.now());

        postRepository.updatePost(updatedPost);

        Post actualPost = postRepository.findPostById(savedPost.getId());

        assertNotNull(actualPost.getId());
        assertNotNull(actualPost.getUser().getId());
        assertEquals(updatedPost.getUser().getFirstName(), actualPost.getUser().getFirstName());
        assertEquals(updatedPost.getUser().getSecondName(), actualPost.getUser().getSecondName());
        assertEquals(updatedPost.getUser().getLastName(), actualPost.getUser().getLastName());
        assertEquals(updatedPost.getUser().getLogin(), actualPost.getUser().getLogin());
        assertEquals(updatedPost.getUser().getPassword(), actualPost.getUser().getPassword());

        assertNotNull(actualPost.getRentConditionInfo().getId());
        assertNotNull(actualPost.getRentConditionInfo().getPost().getId());
        assertEquals(updatedPost.getRentConditionInfo().getDeposit(), actualPost.getRentConditionInfo().getDeposit());
        assertEquals(updatedPost.getRentConditionInfo().getCommissionPercent(), actualPost.getRentConditionInfo().getCommissionPercent());
        assertEquals(updatedPost.getRentConditionInfo().getCurrency(), actualPost.getRentConditionInfo().getCurrency());
        assertEquals(updatedPost.getRentConditionInfo().getTypeOfPayment(), actualPost.getRentConditionInfo().getTypeOfPayment());

        assertNotNull(actualPost.getApartmentInfo().getId());
        assertNotNull(actualPost.getApartmentInfo().getPost().getId());
        assertNotNull(actualPost.getApartmentInfo().getBathroomType().getId());
        assertEquals(updatedPost.getApartmentInfo().getBathroomType().getName(), actualPost.getApartmentInfo().getBathroomType().getName());
        assertNotNull(actualPost.getApartmentInfo().getRepairType().getId());
        assertEquals(updatedPost.getApartmentInfo().getRepairType().getName(), actualPost.getApartmentInfo().getRepairType().getName());
        assertNotNull(updatedPost.getApartmentInfo().getBalconyType().getId());
        assertEquals(updatedPost.getApartmentInfo().getBalconyType().getName(), actualPost.getApartmentInfo().getBalconyType().getName());
        assertNotNull(actualPost.getApartmentInfo().getRoomsType().getId());
        assertEquals(updatedPost.getApartmentInfo().getRoomsType().getName(), actualPost.getApartmentInfo().getRoomsType().getName());
        assertEquals(updatedPost.getApartmentInfo().getRoomsCount(), actualPost.getApartmentInfo().getRoomsCount());
        assertEquals(updatedPost.getApartmentInfo().getTotalArea(), actualPost.getApartmentInfo().getTotalArea());
        assertEquals(updatedPost.getApartmentInfo().getKitchenArea(), actualPost.getApartmentInfo().getKitchenArea());
        assertEquals(updatedPost.getApartmentInfo().getLivingSpace(), actualPost.getApartmentInfo().getLivingSpace());
        assertEquals(updatedPost.getApartmentInfo().getFlour(), actualPost.getApartmentInfo().getFlour());
        assertEquals(updatedPost.getApartmentInfo().getAdditionally(), actualPost.getApartmentInfo().getAdditionally());
        assertEquals(updatedPost.getApartmentInfo().getFurniture(), actualPost.getApartmentInfo().getFurniture());
        assertEquals(updatedPost.getApartmentInfo().getAppliance(), actualPost.getApartmentInfo().getAppliance());

        assertNotNull(actualPost.getHouseInfo().getId());
        assertNotNull(actualPost.getHouseInfo().getPost().getId());
        assertNotNull(actualPost.getHouseInfo().getHouseType().getId());
        assertEquals(updatedPost.getHouseInfo().getHouseType().getName(), actualPost.getHouseInfo().getHouseType().getName());
        assertEquals(updatedPost.getHouseInfo().getAddress(), actualPost.getHouseInfo().getAddress());
        assertEquals(updatedPost.getHouseInfo().getFloursCount(), actualPost.getHouseInfo().getFloursCount());

        assertNotNull(actualPost.getRentType().getId());
        assertEquals(updatedPost.getRentType().getName(), actualPost.getRentType().getName());

        assertEquals(updatedPost.getName(), actualPost.getName());
        assertEquals(updatedPost.getTitle(), actualPost.getTitle());
        assertEquals(updatedPost.getDate(), actualPost.getDate());
    }
}
