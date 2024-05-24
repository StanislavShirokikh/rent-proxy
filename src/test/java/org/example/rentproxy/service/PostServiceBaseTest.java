package org.example.rentproxy.service;

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
import org.example.rentproxy.repository.jpa.UserParameterRepository;
import org.example.rentproxy.repository.jpa.entities.UserParameter;
import org.example.rentproxy.service.integration.currencyService.CurrencyService;
import org.example.rentproxy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class PostServiceBaseTest {
    @MockBean
    protected CurrencyService currencyService;
    @Autowired
    private UserService userService;
    @Autowired
    protected PostService postService;
    @Autowired
    private UserParameterRepository userParameterRepository;


    protected PostDto createPost() {
        Set<TypeOfPaymentDto> typeOfPaymentSet = new HashSet<>();
        TypeOfPaymentDto typeOfPaymentDto = new TypeOfPaymentDto();
        typeOfPaymentDto.setName("Включены в платёж");
        typeOfPaymentSet.add(typeOfPaymentDto);

        RentConditionInfoDto rentConditionInfoDto = new RentConditionInfoDto();
        rentConditionInfoDto.setDeposit(100.0);
        rentConditionInfoDto.setCommissionPercent(50);
        rentConditionInfoDto.setPrice(1000.0);
        rentConditionInfoDto.setCurrency("RUB");
        rentConditionInfoDto.setTypeOfPaymentDto(typeOfPaymentSet);

        ApartmentInfoDto apartmentInfoDto = new ApartmentInfoDto();

        BathroomTypeDto bathroomTypeDto = new BathroomTypeDto();
        bathroomTypeDto.setName("Раздельный");
        apartmentInfoDto.setBathroomTypeDto(bathroomTypeDto);

        RepairTypeDto repairTypeDto = new RepairTypeDto();
        repairTypeDto.setName("Евро");
        apartmentInfoDto.setRepairTypeDto(repairTypeDto);

        BalconyTypeDto balconyTypeDto = new BalconyTypeDto();
        balconyTypeDto.setName("Балкон");
        apartmentInfoDto.setBalconyTypeDto(balconyTypeDto);
        RoomsTypeDto roomsTypeDto = new RoomsTypeDto();
        roomsTypeDto.setName("Изолированные");
        apartmentInfoDto.setRoomsTypeDto(roomsTypeDto);
        apartmentInfoDto.setRoomsCount(3);
        apartmentInfoDto.setTotalArea(74.1);
        apartmentInfoDto.setKitchenArea(9.7);
        apartmentInfoDto.setLivingSpace(51.3);
        apartmentInfoDto.setFlour(9);
        apartmentInfoDto.setAdditionally("Гардеробная");

        Set<FurnitureDto> furnitures = new HashSet<>();
        FurnitureDto furnitureDto1 = new FurnitureDto();
        furnitureDto1.setName("Кухня");
        furnitures.add(furnitureDto1);
        apartmentInfoDto.setFurnitureDto(furnitures);

        Set<ApplianceDto> appliances = new HashSet<>();
        ApplianceDto applianceDto1 = new ApplianceDto();
        applianceDto1.setName("Холодильник");
        ApplianceDto applianceDto2 = new ApplianceDto();
        applianceDto1.setName("Плита");
        ApplianceDto applianceDto3 = new ApplianceDto();
        applianceDto1.setName("Стиральная машина");
        ApplianceDto applianceDto4 = new ApplianceDto();
        applianceDto1.setName("Посудомойка");
        appliances.add(applianceDto1);
        appliances.add(applianceDto2);
        appliances.add(applianceDto3);
        appliances.add(applianceDto4);
        apartmentInfoDto.setApplianceDto(appliances);

        HouseInfoDto houseInfoDto = new HouseInfoDto();
        HouseTypeDto houseTypeDto = new HouseTypeDto();
        houseTypeDto.setName("Кирпичный");
        houseInfoDto.setHouseTypeDto(houseTypeDto);
        houseInfoDto.setAddress("Большевистская 143");
        houseInfoDto.setFloursCount(26);

        PostDto post = new PostDto();
        post.setUserDto(createUser(
                "Имя1",
                "Имя1",
                "Имя1",
                "landlordDeleteOutdatedReservationRequestLogin",
                "landlordDeleteOutdatedReservationRequestPassword"));
        post.setRentConditionInfoDto(rentConditionInfoDto);
        post.setApartmentInfoDto(apartmentInfoDto);
        post.setHouseInfoDto(houseInfoDto);
        RentTypeDto rentTypeDto = new RentTypeDto();
        rentTypeDto.setName("Посуточная");
        post.setRentTypeDto(rentTypeDto);
        post.setName("Сдам 3-х комнатную квартиру");
        post.setTitle("Уютная квартира в тихом месте, метро 10 мин, все необходимое в квартире имеется");
        post.setDate(LocalDate.now());

        return postService.save(post);
    }

    protected UserDto createUser(String firstName, String secondName, String lastName, String login, String password ) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(firstName);
        userDto.setSecondName(secondName);
        userDto.setLastName(lastName);
        userDto.setLogin(login);
        userDto.setPassword(password);

        return userService.createUser(userDto);
    }

    protected UserParameter createUserParameter(long userId) {
        UserParameter userParameter = new UserParameter();
        userParameter.setName("defaultCurrency");
        userParameter.setParamValue("USD");
        userParameter.setUserId(userId);

        return userParameterRepository.save(userParameter);
    }
}
