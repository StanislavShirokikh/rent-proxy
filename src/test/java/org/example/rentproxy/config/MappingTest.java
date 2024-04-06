package org.example.rentproxy.config;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.UserDto;
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
import org.example.rentproxy.repository.entities.RoomsType;
import org.example.rentproxy.repository.entities.TypeOfPayment;
import org.example.rentproxy.repository.entities.User;
import org.example.rentproxy.service.mapper.DtoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MappingTest {
    @Autowired
    private DtoMapper dtoMapper;
    @Test
    public void mapToUserDto() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Иван");
        user.setSecondName("Иванович");
        user.setLastName("Иванов");
        user.setLogin("mr_ivanov");
        user.setPassword("1234");

        UserDto userDto = dtoMapper.map(user, UserDto.class);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getSecondName(), userDto.getSecondName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getLogin(), userDto.getLogin());
    }

    @Test
    public void mapToPostDto() {
        User user = new User();
        user.setId(2L);
        user.setFirstName("Анна");
        user.setSecondName("Ивановна");
        user.setLastName("Иванова");
        user.setLogin("ms_ivanov");
        user.setPassword("12345678");

        RentConditionInfo rentConditionInfo = new RentConditionInfo();
        TypeOfPayment typeOfPayment1 = new TypeOfPayment();
        typeOfPayment1.setId(1L);
        typeOfPayment1.setName("Оплата1");
        TypeOfPayment typeOfPayment2 = new TypeOfPayment();
        typeOfPayment2.setId(2L);
        typeOfPayment2.setName("Оплата2");
        Set<TypeOfPayment> typeOfPaymentSet = Set.of(typeOfPayment1, typeOfPayment2);
        rentConditionInfo.setId(1L);
        rentConditionInfo.setDeposit(10000.0);
        rentConditionInfo.setCommissionPercent(50);
        rentConditionInfo.setPrice(20000.0);
        rentConditionInfo.setCurrency("RUB");
        rentConditionInfo.setTypeOfPayment(typeOfPaymentSet);

        ApartmentInfo apartmentInfo = new ApartmentInfo();
        BathroomType bathroomType = new BathroomType();
        bathroomType.setId(1L);
        bathroomType.setName("Раздельный");
        RepairType repairType = new RepairType();
        repairType.setId(3L);
        repairType.setName("Евро");
        BalconyType balconyType = new BalconyType();
        balconyType.setId(1L);
        balconyType.setName("Балкон");
        RoomsType roomsType = new RoomsType();
        roomsType.setId(1L);
        roomsType.setName("Изолированные");
        Furniture furniture1 = new Furniture();
        furniture1.setId(1L);
        furniture1.setName("Хранение одежды");
        Furniture furniture2 = new Furniture();
        furniture2.setId(2L);
        furniture2.setName("Спальные места");
        Set<Furniture> furnitureSet = Set.of(furniture1, furniture2);
        Appliance appliance1 = new Appliance();
        appliance1.setId(1L);
        appliance1.setName("Плита");
        Appliance apploance2 = new Appliance();
        apploance2.setId(2L);
        apploance2.setName("Холодильник");
        Set<Appliance> applianceSet = Set.of(appliance1, apploance2);
        apartmentInfo.setId(1L);
        apartmentInfo.setBathroomType(bathroomType);
        apartmentInfo.setRepairType(repairType);
        apartmentInfo.setBalconyType(balconyType);
        apartmentInfo.setRoomsType(roomsType);
        apartmentInfo.setRoomsCount(2);
        apartmentInfo.setTotalArea(60.0);
        apartmentInfo.setKitchenArea(8.0);
        apartmentInfo.setLivingSpace(35.0);
        apartmentInfo.setFlour(3);
        apartmentInfo.setAdditionally("Консьерж");
        apartmentInfo.setFurniture(furnitureSet);
        apartmentInfo.setAppliance(applianceSet);

        HouseInfo houseInfo = new HouseInfo();
        HouseType houseType = new HouseType();
        houseType.setId(1L);
        houseType.setName("Кирпичный");
        houseInfo.setId(1L);
        houseInfo.setHouseType(houseType);
        houseInfo.setAddress("ул.Чапаева 27");
        houseInfo.setFloursCount(11);

        RentType rentType = new RentType();
        rentType.setId(1L);
        rentType.setName("Долгосрочная");

        Post post = new Post();
        post.setId(1L);
        post.setUser(user);
        post.setRentConditionInfo(rentConditionInfo);
        post.setApartmentInfo(apartmentInfo);
        post.setHouseInfo(houseInfo);
        post.setRentType(rentType);
        post.setName("Сдается просторная двушка 60 кв/м");
        post.setTitle("Необходимое для комфортного проживания имеется");
        post.setDate(LocalDate.now());

        PostDto postDto = dtoMapper.convertToPostDto(post);

        assertEquals(post.getId(), postDto.getId());
        assertEquals(post.getRentConditionInfo().getId(), postDto.getRentConditionInfoDto().getId());
        assertEquals(post.getRentConditionInfo().getDeposit(), postDto.getRentConditionInfoDto().getDeposit());
        assertEquals(post.getRentConditionInfo().getCommissionPercent(), postDto.getRentConditionInfoDto().getCommissionPercent());
        assertEquals(post.getRentConditionInfo().getPrice(), postDto.getRentConditionInfoDto().getPrice());
        assertEquals(post.getRentConditionInfo().getCurrency(), postDto.getRentConditionInfoDto().getCurrency());
        assertEquals(post.getRentConditionInfo().getTypeOfPayment().size(), postDto.getRentConditionInfoDto().getTypeOfPaymentDto().size());
        assertEquals(post.getApartmentInfo().getBathroomType().getId(), postDto.getApartmentInfoDto().getBathroomTypeDto().getId());
        assertEquals(post.getApartmentInfo().getBathroomType().getName(), postDto.getApartmentInfoDto().getBathroomTypeDto().getName());
        assertEquals(post.getApartmentInfo().getRepairType().getId(), postDto.getApartmentInfoDto().getRepairTypeDto().getId());
        assertEquals(post.getApartmentInfo().getRepairType().getName(), postDto.getApartmentInfoDto().getRepairTypeDto().getName());
        assertEquals(post.getApartmentInfo().getBalconyType().getId(), postDto.getApartmentInfoDto().getBalconyTypeDto().getId());
        assertEquals(post.getApartmentInfo().getBalconyType().getName(), postDto.getApartmentInfoDto().getBalconyTypeDto().getName());
        assertEquals(post.getApartmentInfo().getRoomsType().getId(), postDto.getApartmentInfoDto().getRoomsTypeDto().getId());
        assertEquals(post.getApartmentInfo().getRoomsType().getName(), postDto.getApartmentInfoDto().getRoomsTypeDto().getName());
        assertEquals(post.getApartmentInfo().getFurniture().size(), postDto.getApartmentInfoDto().getFurnitureDto().size());
        assertEquals(post.getHouseInfo().getId(), postDto.getHouseInfoDto().getId());
        assertEquals(post.getHouseInfo().getHouseType().getId(), postDto.getHouseInfoDto().getHouseTypeDto().getId());
        assertEquals(post.getHouseInfo().getHouseType().getName(), postDto.getHouseInfoDto().getHouseTypeDto().getName());
        assertEquals(post.getHouseInfo().getAddress(), postDto.getHouseInfoDto().getAddress());
        assertEquals(post.getHouseInfo().getFloursCount(), postDto.getHouseInfoDto().getFloursCount());
        assertEquals(post.getRentType().getName(), postDto.getRentTypeDto().getName());
        assertEquals(post.getName(), postDto.getName());
        assertEquals(post.getTitle(), postDto.getTitle());
        assertEquals(post.getDate(), postDto.getDate());
    }
}