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
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.filter.PostOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FilterTest {
    @Autowired
    private PostRepository postRepository;
    private Post post1;
    private Post post2;
    private Post post3;
    private Post post4;
    private Post post5;

    @BeforeEach
    void setUpPost() {
        post1 = getSavedPost(
                getUser("Василий",
                        "Афанасьевич",
                        "Петров",
                        "login",
                        "qwerty"
                ),
                getRenConditionInfo(
                        30000.0,
                        50,
                        99999.99,
                        "RUB",
                        "Включены в платёж"
                ),
                getApartmentInfo(
                        "Раздельный",
                        "Евро",
                        "Балкон",
                        "Изолированные",
                        3,
                        74.1,
                        9.7,
                        51.3,
                        9,
                        "Гардеробная",
                        Set.of("Спальные места", "Кухня", "Хранение одежды"),
                        Set.of("Холодильник", "Плита", "Стиральная машина", "Посудомойка")
                ),
                getHouseInfo(
                        "Кирпичный",
                        "Большевистская 143",
                        26
                ),
                "Долгосрочная",
                "Квартира 1",
                "Уютная квартира 1",
                LocalDate.of(2023, Month.MARCH, 15)
        );

        post2 = getSavedPost(
                getUser("Дмитрий",
                        "Петрович",
                        "Васнецов",
                        "dpv",
                        "asdf"
                ),
                getRenConditionInfo(
                        40000.0,
                        50,
                        111111.11,
                        "RUB",
                        "Оплачиваются отдельно"
                ),
                getApartmentInfo(
                        "Совмещенный",
                        "Требуется",
                        "Лоджия",
                        "Смежные",
                        2,
                        50.1,
                        7.7,
                        28.3,
                        9,
                        "Кладовка",
                        Set.of("Кухня", "Хранение одежды"),
                        Set.of("Холодильник", "Плита")
                ),
                getHouseInfo(
                        "Монолитно-кирпичный",
                        "Гоголя 143",
                        9
                ),
                "Долгосрочная",
                "Квартира 2",
                "Нужен ремонт квартира 2",
                LocalDate.of(2023, Month.MARCH, 16)
        );
        post3 = getSavedPost(
                getUser("Петр",
                        "Сергеевич",
                        "Киселев",
                        "psk",
                        "zxcvb"
                ),
                getRenConditionInfo(
                        1500.0,
                        10,
                        3000.0,
                        "RUB",
                        "Включены в платёж"
                ),
                getApartmentInfo(
                        "Раздельный",
                        "Дизайнерский",
                        "Балкон",
                        "Изолированные",
                        1,
                        20.0,
                        5.0,
                        10.0,
                        15,
                        "Завтрак",
                        Set.of("Спальные места", "Кухня", "Хранение одежды"),
                        Set.of("Холодильник", "Плита", "Стиральная машина")
                ),
                getHouseInfo(
                        "Кирпичный",
                        "Дунаевского 143",
                        16
                ),
                "Посуточная",
                "Квартира 3",
                "Классная квартира 3",
                LocalDate.of(2023, Month.MARCH, 17)
        );
        post4 = getSavedPost(
                getUser("Иннокентий",
                        "Кириллович",
                        "Перов",
                        "ikp",
                        "yuio"
                ),
                getRenConditionInfo(
                        45000.0,
                        60,
                        90000.0,
                        "RUB",
                        "Включены в платёж"
                ),
                getApartmentInfo(
                        "Раздельный",
                        "Евро",
                        "Лоджия",
                        "Изолированные",
                        3,
                        104.1,
                        15.7,
                        70.3,
                        5,
                        "Гардеробная",
                        Set.of("Спальные места", "Кухня", "Хранение одежды"),
                        Set.of("Холодильник", "Плита", "Стиральная машина", "Посудомойка")
                ),
                getHouseInfo(
                        "Кирпичный",
                        "Адмиралтейская 143",
                        7
                ),
                "Долгосрочная",
                "Квартира 4",
                "Большая квартира 4",
                LocalDate.of(2023, Month.MARCH, 18)
        );
        post5 = getSavedPost(
                getUser("Алексей",
                        "Тимофеевич",
                        "Светлов",
                        "atc",
                        "ghjk"
                ),
                getRenConditionInfo(
                        1000.0,
                        40,
                        2000.0,
                        "USD",
                        "Включены в платёж"
                ),
                getApartmentInfo(
                        "Совмещенный",
                        "Евро",
                        "Балкон",
                        "Изолированные",
                        4,
                        120.0,
                        20.7,
                        69.3,
                        5,
                        "Гардеробная",
                        Set.of("Спальные места", "Кухня", "Хранение одежды"),
                        Set.of("Холодильник", "Плита", "Стиральная машина", "Посудомойка")
                ),
                getHouseInfo(
                        "Кирпичный",
                        "Октябрьская 143",
                        5
                ),
                "Долгосрочная",
                "Квартира 5",
                "Семейная квартира 5",
                LocalDate.of(2023, Month.MARCH, 19)
        );
    }


    @Test
    void findPostsByRentType() {
        Filter filter = new Filter();
        filter.setRentType("Долгосрочная");
        filter.setPostOrder(PostOrder.ASCENDING_DATE);
        filter.setPageNumber(0);
        filter.setPageSize(5);

        List<Post> actualList = postRepository.findPostByFilter(filter);

        assertEquals(4, actualList.size());
        for (Post post: actualList) {
            assertEquals(filter.getRentType(), post.getRentType().getName());
        }
        assertEquals(post1.getName(), actualList.get(0).getName());
        assertEquals(post2.getName(), actualList.get(1).getName());
        assertEquals(post4.getName(), actualList.get(2).getName());
        assertEquals(post5.getName(), actualList.get(3).getName());
    }

    @Test
    void findPostsByMinAndMaxPriceType() {
        Filter filter = new Filter();
        filter.setMinPrice(99999.99);
        filter.setMaxPrice(111111.11);
        filter.setPostOrder(PostOrder.ASCENDING_DATE);
        filter.setPageNumber(0);
        filter.setPageSize(5);

        List<Post> actualList = postRepository.findPostByFilter(filter);

        assertEquals(2, actualList.size());
        assertEquals(post1.getRentConditionInfo().getPrice(), actualList.get(0).getRentConditionInfo().getPrice());
        assertEquals(post2.getRentConditionInfo().getPrice(), actualList.get(1).getRentConditionInfo().getPrice());
        assertEquals(post1.getName(), actualList.get(0).getName());
        assertEquals(post2.getName(), actualList.get(1).getName());
    }

    @Test
    void findPostsByFurniture() {
        Filter filter = new Filter();
        filter.setFurniture(Set.of("Спальные места"));
        filter.setPostOrder(PostOrder.ASCENDING_DATE);
        filter.setPageNumber(0);
        filter.setPageSize(5);

        List<Post> actualList = postRepository.findPostByFilter(filter);

        assertEquals(4, actualList.size());
        assertEquals(post1.getApartmentInfo().getFurniture(), actualList.get(0).getApartmentInfo().getFurniture());
        assertEquals(post3.getApartmentInfo().getFurniture(), actualList.get(1).getApartmentInfo().getFurniture());
        assertEquals(post4.getApartmentInfo().getFurniture(), actualList.get(2).getApartmentInfo().getFurniture());
        assertEquals(post5.getApartmentInfo().getFurniture(), actualList.get(3).getApartmentInfo().getFurniture());
    }

    private Post getSavedPost(User user,
                              RentConditionInfo rentConditionInfo,
                              ApartmentInfo apartmentInfo,
                              HouseInfo houseInfo,
                              String rentTypeName,
                              String name,
                              String title,
                              LocalDate date) {

        Post post = new Post();
        post.setUser(user);
        rentConditionInfo.setPost(post);
        post.setRentConditionInfo(rentConditionInfo);
        apartmentInfo.setPost(post);
        post.setApartmentInfo(apartmentInfo);
        apartmentInfo.setPost(post);
        post.setHouseInfo(houseInfo);
        RentType rentType = new RentType();
        rentType.setName(rentTypeName);
        post.setRentType(rentType);
        post.setName(name);
        post.setTitle(title);
        post.setDate(date);

       return postRepository.save(post);
    }

    private User getUser(String firstName, String secondName, String lastName, String login, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);
        return user;
    }
    private RentConditionInfo getRenConditionInfo(Double deposit,
                                                  Integer commissionPercent,
                                                  Double price,
                                                  String currency,
                                                  String typeOfPaymentName) {
        Set<TypeOfPayment> typeOfPaymentSet = new HashSet<>();
        TypeOfPayment typeOfPayment = new TypeOfPayment();
        typeOfPayment.setName(typeOfPaymentName);
        typeOfPaymentSet.add(typeOfPayment);

        RentConditionInfo rentConditionInfo = new RentConditionInfo();
        rentConditionInfo.setDeposit(deposit);
        rentConditionInfo.setCommissionPercent(commissionPercent);
        rentConditionInfo.setPrice(price);
        rentConditionInfo.setCurrency(currency);
        rentConditionInfo.setTypeOfPayment(typeOfPaymentSet);

        return rentConditionInfo;
    }
    private ApartmentInfo getApartmentInfo(String bathRoomTypeName,
                                           String repairTypeName,
                                           String balconyTypeName,
                                           String roomsTypeName,
                                           Integer roomsCount,
                                           Double totalArea,
                                           Double kitchenArea,
                                           Double livingSpace,
                                           Integer flour,
                                           String additionally,
                                           Set<String> furnitureNames,
                                           Set<String> applianceNames) {
        ApartmentInfo apartmentInfo = new ApartmentInfo();

        BathroomType bathroomType = new BathroomType();
        bathroomType.setName(bathRoomTypeName);
        apartmentInfo.setBathroomType(bathroomType);

        RepairType repairType = new RepairType();
        repairType.setName(repairTypeName);
        apartmentInfo.setRepairType(repairType);

        BalconyType balconyType = new BalconyType();
        balconyType.setName(balconyTypeName);
        apartmentInfo.setBalconyType(balconyType);
        RoomsType roomsType = new RoomsType();
        roomsType.setName(roomsTypeName);
        apartmentInfo.setRoomsType(roomsType);
        apartmentInfo.setRoomsCount(roomsCount);
        apartmentInfo.setTotalArea(totalArea);
        apartmentInfo.setKitchenArea(kitchenArea);
        apartmentInfo.setLivingSpace(livingSpace);
        apartmentInfo.setFlour(flour);
        apartmentInfo.setAdditionally(additionally);

        Set<Furniture> furnitureSet = new HashSet<>();
        for (String name: furnitureNames) {
            Furniture furniture = new Furniture();
            furniture.setName(name);
            furnitureSet.add(furniture);
        }
        apartmentInfo.setFurniture(furnitureSet);

        Set<Appliance> applianceSet = new HashSet<>();
        for (String name: applianceNames) {
            Appliance appliance = new Appliance();
            appliance.setName(name);
            applianceSet.add(appliance);
        }
        apartmentInfo.setAppliance(applianceSet);

        return apartmentInfo;
    }

    private HouseInfo getHouseInfo(String houseTypeName, String address, Integer floursCount) {
        HouseInfo houseInfo = new HouseInfo();
        HouseType houseType = new HouseType();
        houseType.setName(houseTypeName);
        houseInfo.setHouseType(houseType);
        houseInfo.setAddress(address);
        houseInfo.setFloursCount(floursCount);

        return houseInfo;
    }




}
