package org.example.rentproxy.config;

import org.example.rentproxy.dto.ApartmentInfoDto;
import org.example.rentproxy.dto.ApplianceDto;
import org.example.rentproxy.dto.BalconyTypeDto;
import org.example.rentproxy.dto.BathroomTypeDto;
import org.example.rentproxy.dto.FurnitureDto;
import org.example.rentproxy.dto.HouseInfoDto;
import org.example.rentproxy.dto.HouseTypeDto;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.RentConditionInfoDto;
import org.example.rentproxy.dto.RentTypeDto;
import org.example.rentproxy.dto.RepairTypeDto;
import org.example.rentproxy.dto.RoomsTypeDto;
import org.example.rentproxy.dto.TypeOfPaymentDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.mapper.PostDtoMapper;
import org.example.rentproxy.mapper.PostMapper;
import org.example.rentproxy.mapper.PostResponseMapper;
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
import org.example.rentproxy.response.PostResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MappingTest {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostDtoMapper postDtoMapper;
    @Autowired
    private PostResponseMapper postResponseMapper;

    @Test
    public void mapToUserDto() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Иван");
        user.setSecondName("Иванович");
        user.setLastName("Иванов");
        user.setLogin("mr_ivanov");
        user.setPassword("1234");

        UserDto userDto = postDtoMapper.map(user, UserDto.class);

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

        PostDto postDto = postDtoMapper.convertToPostDto(post);

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

    @Test
    public void mapToPost() {
        UserDto userDto = new UserDto();
        userDto.setId(2L);
        userDto.setFirstName("Анна");
        userDto.setSecondName("Ивановна");
        userDto.setLastName("Иванова");
        userDto.setLogin("ms_ivanova");

        RentConditionInfoDto rentConditionInfoDto = new RentConditionInfoDto();
        rentConditionInfoDto.setId(1L);
        rentConditionInfoDto.setDeposit(10000.0);
        rentConditionInfoDto.setCommissionPercent(50);
        rentConditionInfoDto.setPrice(20000.0);
        rentConditionInfoDto.setCurrency("RUB");

        TypeOfPaymentDto typeOfPaymentDto1 = new TypeOfPaymentDto();
        typeOfPaymentDto1.setId(1L);
        typeOfPaymentDto1.setName("Оплата1");
        TypeOfPaymentDto typeOfPaymentDto2 = new TypeOfPaymentDto();
        typeOfPaymentDto2.setId(2L);
        typeOfPaymentDto2.setName("Оплата2");
        Set<TypeOfPaymentDto> typeOfPaymentDtoSet = Set.of(typeOfPaymentDto1, typeOfPaymentDto2);
        rentConditionInfoDto.setTypeOfPaymentDto(typeOfPaymentDtoSet);

        ApartmentInfoDto apartmentInfoDto = new ApartmentInfoDto();
        apartmentInfoDto.setId(1L);
        apartmentInfoDto.setRoomsCount(2);
        apartmentInfoDto.setTotalArea(60.0);
        apartmentInfoDto.setKitchenArea(8.0);
        apartmentInfoDto.setLivingSpace(35.0);
        apartmentInfoDto.setFlour(3);
        apartmentInfoDto.setAdditionally("Консьерж");

        BathroomTypeDto bathroomTypeDto = new BathroomTypeDto();
        bathroomTypeDto.setId(1L);
        bathroomTypeDto.setName("Раздельный");

        RepairTypeDto repairTypeDto = new RepairTypeDto();
        repairTypeDto.setId(3L);
        repairTypeDto.setName("Евро");

        BalconyTypeDto balconyTypeDto = new BalconyTypeDto();
        balconyTypeDto.setId(1L);
        balconyTypeDto.setName("Балкон");

        RoomsTypeDto roomsTypeDto = new RoomsTypeDto();
        roomsTypeDto.setId(1L);
        roomsTypeDto.setName("Изолированные");

        apartmentInfoDto.setBathroomTypeDto(bathroomTypeDto);
        apartmentInfoDto.setRepairTypeDto(repairTypeDto);
        apartmentInfoDto.setBalconyTypeDto(balconyTypeDto);
        apartmentInfoDto.setRoomsTypeDto(roomsTypeDto);

        FurnitureDto furnitureDto1 = new FurnitureDto();
        furnitureDto1.setId(1L);
        furnitureDto1.setName("Хранение одежды");
        FurnitureDto furnitureDto2 = new FurnitureDto();
        furnitureDto2.setId(2L);
        furnitureDto2.setName("Спальные места");
        Set<FurnitureDto> furnitureDtoSet = Set.of(furnitureDto1, furnitureDto2);
        apartmentInfoDto.setFurnitureDto(furnitureDtoSet);

        ApplianceDto applianceDto1 = new ApplianceDto();
        applianceDto1.setId(1L);
        applianceDto1.setName("Плита");
        ApplianceDto applianceDto2 = new ApplianceDto();
        applianceDto2.setId(2L);
        applianceDto2.setName("Холодильник");
        Set<ApplianceDto> applianceDtoSet = Set.of(applianceDto1, applianceDto2);
        apartmentInfoDto.setApplianceDto(applianceDtoSet);

        HouseInfoDto houseInfoDto = new HouseInfoDto();
        houseInfoDto.setId(1L);
        houseInfoDto.setAddress("ул.Чапаева 27");
        houseInfoDto.setFloursCount(11);

        HouseTypeDto houseTypeDto = new HouseTypeDto();
        houseTypeDto.setId(1L);
        houseTypeDto.setName("Кирпичный");
        houseInfoDto.setHouseTypeDto(houseTypeDto);

        RentTypeDto rentTypeDto = new RentTypeDto();
        rentTypeDto.setId(1L);
        rentTypeDto.setName("Долгосрочная");

        PostDto postDto = new PostDto();
        postDto.setId(1L);
        postDto.setUserDto(userDto);
        postDto.setRentConditionInfoDto(rentConditionInfoDto);
        postDto.setApartmentInfoDto(apartmentInfoDto);
        postDto.setHouseInfoDto(houseInfoDto);
        postDto.setRentTypeDto(rentTypeDto);
        postDto.setName("Сдается просторная двушка 60 кв/м");
        postDto.setTitle("Необходимое для комфортного проживания имеется");
        postDto.setDate(LocalDate.now());

        Post post = postMapper.convertToPost(postDto);

        assertEquals(postDto.getRentConditionInfoDto().getDeposit(), post.getRentConditionInfo().getDeposit());
        assertEquals(postDto.getRentConditionInfoDto().getCommissionPercent(), post.getRentConditionInfo().getCommissionPercent());
        assertEquals(postDto.getRentConditionInfoDto().getPrice(), post.getRentConditionInfo().getPrice());
        assertEquals(postDto.getRentConditionInfoDto().getCurrency(), post.getRentConditionInfo().getCurrency());
        assertEquals(postDto.getRentConditionInfoDto().getTypeOfPaymentDto().size(), post.getRentConditionInfo().getTypeOfPayment().size());
        assertEquals(postDto.getApartmentInfoDto().getBathroomTypeDto().getName(), post.getApartmentInfo().getBathroomType().getName());
        assertEquals(postDto.getApartmentInfoDto().getRepairTypeDto().getName(), post.getApartmentInfo().getRepairType().getName());
        assertEquals(postDto.getApartmentInfoDto().getBalconyTypeDto().getName(), post.getApartmentInfo().getBalconyType().getName());
        assertEquals(postDto.getApartmentInfoDto().getRoomsTypeDto().getName(), post.getApartmentInfo().getRoomsType().getName());
        assertEquals(postDto.getApartmentInfoDto().getFurnitureDto().size(), post.getApartmentInfo().getFurniture().size());
        assertEquals(postDto.getHouseInfoDto().getHouseTypeDto().getName(), post.getHouseInfo().getHouseType().getName());
        assertEquals(postDto.getHouseInfoDto().getAddress(), post.getHouseInfo().getAddress());
        assertEquals(postDto.getHouseInfoDto().getFloursCount(), post.getHouseInfo().getFloursCount());
        assertEquals(postDto.getRentTypeDto().getName(), post.getRentType().getName());
        assertEquals(postDto.getName(), post.getName());
        assertEquals(postDto.getTitle(), post.getTitle());
        assertEquals(postDto.getDate(), post.getDate());
    }

    @Test
    public void mapToPostResponse() {
        UserDto userDto = new UserDto();
        userDto.setId(3L);
        userDto.setFirstName("Инна");
        userDto.setSecondName("Ивановна");
        userDto.setLastName("Парфенова");
        userDto.setLogin("ms_parfenova");

        RentConditionInfoDto rentConditionInfoDto = new RentConditionInfoDto();
        rentConditionInfoDto.setId(3L);
        rentConditionInfoDto.setDeposit(11111.0);
        rentConditionInfoDto.setCommissionPercent(44);
        rentConditionInfoDto.setPrice(22222.0);
        rentConditionInfoDto.setCurrency("RUB");

        TypeOfPaymentDto typeOfPaymentDto1 = new TypeOfPaymentDto();
        typeOfPaymentDto1.setId(3L);
        typeOfPaymentDto1.setName("Оплата3");
        TypeOfPaymentDto typeOfPaymentDto2 = new TypeOfPaymentDto();
        typeOfPaymentDto2.setId(4L);
        typeOfPaymentDto2.setName("Оплата4");
        Set<TypeOfPaymentDto> typeOfPaymentDtoSet = Set.of(typeOfPaymentDto1, typeOfPaymentDto2);
        rentConditionInfoDto.setTypeOfPaymentDto(typeOfPaymentDtoSet);

        ApartmentInfoDto apartmentInfoDto = new ApartmentInfoDto();
        apartmentInfoDto.setId(3L);
        apartmentInfoDto.setRoomsCount(11);
        apartmentInfoDto.setTotalArea(111.0);
        apartmentInfoDto.setKitchenArea(11.0);
        apartmentInfoDto.setLivingSpace(66.0);
        apartmentInfoDto.setFlour(23);
        apartmentInfoDto.setAdditionally("Дополнительно");

        BathroomTypeDto bathroomTypeDto = new BathroomTypeDto();
        bathroomTypeDto.setId(1L);
        bathroomTypeDto.setName("Раздельный");

        RepairTypeDto repairTypeDto = new RepairTypeDto();
        repairTypeDto.setId(3L);
        repairTypeDto.setName("Евро");

        BalconyTypeDto balconyTypeDto = new BalconyTypeDto();
        balconyTypeDto.setId(1L);
        balconyTypeDto.setName("Балкон");

        RoomsTypeDto roomsTypeDto = new RoomsTypeDto();
        roomsTypeDto.setId(1L);
        roomsTypeDto.setName("Изолированные");

        apartmentInfoDto.setBathroomTypeDto(bathroomTypeDto);
        apartmentInfoDto.setRepairTypeDto(repairTypeDto);
        apartmentInfoDto.setBalconyTypeDto(balconyTypeDto);
        apartmentInfoDto.setRoomsTypeDto(roomsTypeDto);

        FurnitureDto furnitureDto1 = new FurnitureDto();
        furnitureDto1.setId(1L);
        furnitureDto1.setName("Хранение одежды");
        FurnitureDto furnitureDto2 = new FurnitureDto();
        furnitureDto2.setId(2L);
        furnitureDto2.setName("Спальные места");
        Set<FurnitureDto> furnitureDtoSet = Set.of(furnitureDto1, furnitureDto2);
        apartmentInfoDto.setFurnitureDto(furnitureDtoSet);

        ApplianceDto applianceDto1 = new ApplianceDto();
        applianceDto1.setId(1L);
        applianceDto1.setName("Плита");
        ApplianceDto applianceDto2 = new ApplianceDto();
        applianceDto2.setId(2L);
        applianceDto2.setName("Холодильник");
        Set<ApplianceDto> applianceDtoSet = Set.of(applianceDto1, applianceDto2);
        apartmentInfoDto.setApplianceDto(applianceDtoSet);

        HouseInfoDto houseInfoDto = new HouseInfoDto();
        houseInfoDto.setId(1L);
        houseInfoDto.setAddress("ул.Чаплыгина");
        houseInfoDto.setFloursCount(35);

        HouseTypeDto houseTypeDto = new HouseTypeDto();
        houseTypeDto.setId(1L);
        houseTypeDto.setName("Кирпичный");
        houseInfoDto.setHouseTypeDto(houseTypeDto);

        RentTypeDto rentTypeDto = new RentTypeDto();
        rentTypeDto.setId(1L);
        rentTypeDto.setName("Долгосрочная");

        PostDto postDto = new PostDto();
        postDto.setId(1L);
        postDto.setUserDto(userDto);
        postDto.setRentConditionInfoDto(rentConditionInfoDto);
        postDto.setApartmentInfoDto(apartmentInfoDto);
        postDto.setHouseInfoDto(houseInfoDto);
        postDto.setRentTypeDto(rentTypeDto);
        postDto.setName("Сдается квартира 111 кв/м");
        postDto.setTitle("Супер класс");
        postDto.setDate(LocalDate.now());

        PostResponse postResponse = postResponseMapper.convertToPostResponse(postDto);

        assertEquals(
                postDto.getRentConditionInfoDto().getDeposit(),
                postResponse.getRentConditionInfoResponse().getDeposit()
        );
        assertEquals(
                postDto.getRentConditionInfoDto().getCommissionPercent(),
                postResponse.getRentConditionInfoResponse().getCommissionPercent()
        );
        assertEquals(
                postDto.getRentConditionInfoDto().getPrice(),
                postResponse.getRentConditionInfoResponse().getPrice()
        );
        assertEquals(
                postDto.getRentConditionInfoDto().getCurrency(),
                postResponse.getRentConditionInfoResponse().getCurrency()
        );
        assertEquals(
                postDto.getRentConditionInfoDto().getTypeOfPaymentDto().size(),
                postResponse.getRentConditionInfoResponse().getTypeOfPaymentResponse().size()
        );
        assertEquals(postDto.getApartmentInfoDto().getBathroomTypeDto().getName(),
                postResponse.getApartmentInfoResponse().getBathroomTypeResponse().getName()
        );
        assertEquals(
                postDto.getApartmentInfoDto().getRepairTypeDto().getName(),
                postResponse.getApartmentInfoResponse().getRepairTypeResponse().getName()
        );
        assertEquals(
                postDto.getApartmentInfoDto().getBalconyTypeDto().getName(),
                postResponse.getApartmentInfoResponse().getBalconyTypeResponse().getName()
        );
        assertEquals(
                postDto.getApartmentInfoDto().getRoomsTypeDto().getName(),
                postResponse.getApartmentInfoResponse().getRoomsTypeResponse().getName()
        );
        assertEquals(
                postDto.getApartmentInfoDto().getFurnitureDto().size(),
                postResponse.getApartmentInfoResponse().getFurnitureResponse().size()
        );
        assertEquals(
                postDto.getHouseInfoDto().getHouseTypeDto().getName(),
                postResponse.getHouseInfoResponse().getHouseTypeResponse().getName()
        );
        assertEquals(postDto.getHouseInfoDto().getAddress(), postResponse.getHouseInfoResponse().getAddress());
        assertEquals(postDto.getHouseInfoDto().getFloursCount(), postResponse.getHouseInfoResponse().getFloursCount());
        assertEquals(postDto.getRentTypeDto().getName(), postResponse.getRentTypeResponse().getName());
        assertEquals(postDto.getName(), postResponse.getName());
        assertEquals(postDto.getTitle(), postResponse.getTitle());
        assertEquals(postDto.getDate(), postResponse.getDate());
    }
}