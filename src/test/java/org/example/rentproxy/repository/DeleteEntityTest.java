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

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class DeleteEntityTest {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RentConditionInfoRepository rentConditionInfoRepository;
    @Autowired
    private ApartmentInfoRepository apartmentInfoRepository;
    @Autowired
    private HouseInfoRepository houseInfoRepository;
    @Test
    public void deletePost() {
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
        furnitures.add(furniture1);
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
        post.setUser(user);
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
        postRepository.deletePostById(savedPost.getId());
        assertNull(postRepository.findPostById(savedPost.getId()));
        assertNull(rentConditionInfoRepository.findRentConditionInfoByPostId(savedPost.getId()));
        assertNull(apartmentInfoRepository.findApartmentInfoByPostId(savedPost.getId()));
        assertNull(houseInfoRepository.findHouseInfoByPostId(savedPost.getId()));
    }
}
