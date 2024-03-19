package org.example.rentproxy.repository;

import org.example.rentproxy.entities.ApartmentInfo;
import org.example.rentproxy.entities.Appliance;
import org.example.rentproxy.entities.BalconyType;
import org.example.rentproxy.entities.BathroomType;
import org.example.rentproxy.entities.Furniture;
import org.example.rentproxy.entities.HouseInfo;
import org.example.rentproxy.entities.HouseType;
import org.example.rentproxy.entities.Post;
import org.example.rentproxy.entities.RentConditionInfo;
import org.example.rentproxy.entities.RentType;
import org.example.rentproxy.entities.RepairType;
import org.example.rentproxy.entities.RoomsType;
import org.example.rentproxy.entities.TypeOfPayment;
import org.example.rentproxy.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SaveEntityTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ApplianceRepository applianceRepository;
    @Autowired
    private BalconyTypeRepository balconyTypeRepository;
    @Autowired
    private BathroomTypeRepository bathroomTypeRepository;
    @Autowired
    private FurnitureRepository furnitureRepository;
    @Autowired
    private HouseTypeRepository houseTypeRepository;
    @Autowired
    private RentTypeRepository rentTypeRepository;
    @Autowired
    private RepairTypeRepository repairTypeRepository;
    @Autowired
    private RoomsTypeRepository roomsTypeRepository;
    @Autowired
    private TypeOfPaymentRepository typeOfPaymentRepository;

    @Test
    public void savePost() {
        Set<TypeOfPayment> typeOfPaymentSet = new HashSet<>();
        typeOfPaymentSet.add(typeOfPaymentRepository.findByName("Долгосрочная"));

        RentConditionInfo rentConditionInfo = new RentConditionInfo();
        rentConditionInfo.setDeposit(30000.0);
        rentConditionInfo.setCommissionPercent(50);
        rentConditionInfo.setPrice(60000.0);
        rentConditionInfo.setCurrency("RUB");
        rentConditionInfo.setTypeOfPayment(typeOfPaymentSet);

        ApartmentInfo apartmentInfo = new ApartmentInfo();
        apartmentInfo.setBathroomType(bathroomTypeRepository.findByName("Раздельный"));
        apartmentInfo.setRepairType(repairTypeRepository.findByName("Евро"));
        apartmentInfo.setBalconyType(balconyTypeRepository.findByName("Балкон"));
        apartmentInfo.setRoomsType(roomsTypeRepository.findByName("Изолированные"));
        apartmentInfo.setRoomsCount(3);
        apartmentInfo.setTotalArea(74.1);
        apartmentInfo.setKitchenArea(9.7);
        apartmentInfo.setLivingSpace(51.3);
        apartmentInfo.setFlour(9);
        apartmentInfo.setAdditionally("Гардеробная");

        Set<Furniture> furniture = new HashSet<>();
        furniture.add(furnitureRepository.findByName("Спальные места"));
        furniture.add(furnitureRepository.findByName("Кухня"));
        furniture.add(furnitureRepository.findByName("Хранение одежды"));
        apartmentInfo.setFurniture(furniture);

        Set<Appliance> appliances = new HashSet<>();
        appliances.add(applianceRepository.findByName("Холодильник"));
        appliances.add(applianceRepository.findByName("Плита"));
        appliances.add(applianceRepository.findByName("Стиральная машина"));
        appliances.add(applianceRepository.findByName("Посудомойка"));
        apartmentInfo.setAppliance(appliances);

        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setHouseType(houseTypeRepository.findByName("Кирпичный"));
        houseInfo.setAddress("Большевистская 143");
        houseInfo.setFloursCount(26);

        User user = new User();
        user.setFirstName("Петр");
        user.setSecondName("Иванович");
        user.setLastName("Иванов");
        user.setLogin("IvanLogin");
        user.setPassword("1234");


        Post post = new Post();
        post.setUser(user);
        post.setRentConditionInfo(rentConditionInfo);
        post.setApartmentInfo(apartmentInfo);
        post.setHouseInfo(houseInfo);
        RentType rentType = rentTypeRepository.findByName("Долгосрочная");
        post.setRentType(rentType);
        post.setName("Сдам 3-х комнатную квартиру");
        post.setTitle("Уютная квартира в тихом месте, метро 10 мин, все необходимое в квартире имеется");
        post.setDate(LocalDate.now());

        rentConditionInfo.setPost(post);
        apartmentInfo.setPost(post);
        houseInfo.setPost(post);

        Post savedPost = null;// = postJpaRepository.save(post);
        assertEquals(user, savedPost.getUser());
        assertEquals(rentConditionInfo, savedPost.getRentConditionInfo());
        assertEquals(apartmentInfo, savedPost.getApartmentInfo());
        assertEquals(houseInfo, savedPost.getHouseInfo());
        assertEquals(rentType, savedPost.getRentType());
        assertEquals(post.getName(), savedPost.getName());
        assertEquals(post.getTitle(), savedPost.getTitle());
        assertEquals(post.getDate(), savedPost.getDate());
    }

    @Test
    public void savedPost2() {
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
        furniture1.setName("Спальные места");
        Furniture furniture2 = new Furniture();
        furniture1.setName("Кухня");
        Furniture furniture3 = new Furniture();
        furniture1.setName("Хранение одежды");
        furnitures.add(furniture1);
        furnitures.add(furniture2);
        furnitures.add(furniture3);
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
        post.setUser(user);
        post.setRentConditionInfo(rentConditionInfo);
        post.setApartmentInfo(apartmentInfo);
        post.setHouseInfo(houseInfo);
//        RentType rentType = new RentType();
//        rentType.setName("Долгосрочная");
//        post.setRentType(rentType);
        post.setName("Сдам 3-х комнатную квартиру");
        post.setTitle("Уютная квартира в тихом месте, метро 10 мин, все необходимое в квартире имеется");
        post.setDate(LocalDate.now());

        rentConditionInfo.setPost(post);
        apartmentInfo.setPost(post);
        houseInfo.setPost(post);

        Post savedPost = postRepository.save(post);
        assertEquals(user, savedPost.getUser());
        assertEquals(rentConditionInfo, savedPost.getRentConditionInfo());
        assertEquals(apartmentInfo, savedPost.getApartmentInfo());
        assertEquals(houseInfo, savedPost.getHouseInfo());
//        assertEquals(rentType, savedPost.getRentType());
        assertEquals(post.getName(), savedPost.getName());
        assertEquals(post.getTitle(), savedPost.getTitle());
        assertEquals(post.getDate(), savedPost.getDate());
    }
}