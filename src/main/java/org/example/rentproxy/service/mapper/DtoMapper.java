package org.example.rentproxy.service.mapper;

import org.example.rentproxy.dto.ApartmentInfoDto;
import org.example.rentproxy.dto.ApplianceDto;
import org.example.rentproxy.dto.BalconyTypeDto;
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
import org.example.rentproxy.repository.entities.Appliance;
import org.example.rentproxy.repository.entities.Furniture;
import org.example.rentproxy.repository.entities.Post;
import org.example.rentproxy.repository.entities.TypeOfPayment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DtoMapper extends ModelMapper {
    public PostDto convertToPostDto(Post post) {
        UserDto userDto = map(post.getUser(), UserDto.class);

        RentConditionInfoDto rentConditionInfoDto = map(post.getRentConditionInfo(), RentConditionInfoDto.class);
        Set<TypeOfPaymentDto> typeOfPaymentDtoSet = mapTypeOfPaymentSet(post.getRentConditionInfo().getTypeOfPayment());
        rentConditionInfoDto.setTypeOfPaymentDto(typeOfPaymentDtoSet);

        ApartmentInfoDto apartmentInfoDto = map(post.getApartmentInfo(), ApartmentInfoDto.class);
        BalconyTypeDto balconyTypeDto = map(post.getApartmentInfo().getBalconyType(), BalconyTypeDto.class);
        RepairTypeDto repairTypeDto = map(post.getApartmentInfo().getRepairType(), RepairTypeDto.class);
        RoomsTypeDto roomsTypeDto = map(post.getApartmentInfo().getRoomsType(), RoomsTypeDto.class);
        Set<FurnitureDto> furnitureDtoSet = mapFurnitureSet(post.getApartmentInfo().getFurniture());
        Set<ApplianceDto> applianceDtoSet = mapApplianceSet(post.getApartmentInfo().getAppliance());
        apartmentInfoDto.setRoomsTypeDto(roomsTypeDto);
        apartmentInfoDto.setRepairTypeDto(repairTypeDto);
        apartmentInfoDto.setBalconyTypeDto(balconyTypeDto);
        apartmentInfoDto.setFurnitureDto(furnitureDtoSet);
        apartmentInfoDto.setApplianceDto(applianceDtoSet);

        HouseInfoDto  houseInfoDto = map(post.getHouseInfo(), HouseInfoDto.class);
        HouseTypeDto houseTypeDto = map(post.getHouseInfo().getHouseType(), HouseTypeDto.class);
        houseInfoDto.setHouseTypeDto(houseTypeDto);

        RentTypeDto rentTypeDto = map(post.getRentType(), RentTypeDto.class);

        PostDto postDto = map(post, PostDto.class);
        postDto.setUserDto(userDto);
        postDto.setRentConditionInfoDto(rentConditionInfoDto);
        postDto.setApartmentInfoDto(apartmentInfoDto);
        postDto.setHouseInfoDto(houseInfoDto);
        postDto.setRentTypeDto(rentTypeDto);

        return postDto;
    }

    private Set<TypeOfPaymentDto> mapTypeOfPaymentSet(Set<TypeOfPayment> typeOfPaymentSet) {
        Set<TypeOfPaymentDto> typeOfPaymentDtoSet = new HashSet<>();
        for (TypeOfPayment typeOfPayment : typeOfPaymentSet) {
            typeOfPaymentDtoSet.add(map(typeOfPayment, TypeOfPaymentDto.class));
        }
        return typeOfPaymentDtoSet;
    }

    private Set<FurnitureDto> mapFurnitureSet(Set<Furniture> furnitureSet) {
        Set<FurnitureDto> furnitureDtoSet = new HashSet<>();
        for (Furniture furniture : furnitureSet) {
            furnitureDtoSet.add(map(furniture, FurnitureDto.class));
        }
        return furnitureDtoSet;
    }

    private Set<ApplianceDto> mapApplianceSet(Set<Appliance> applianceSet) {
        Set<ApplianceDto> applianceDtoSet = new HashSet<>();
        for (Appliance appliance : applianceSet) {
            applianceDtoSet.add(map(appliance, ApplianceDto.class));
        }
        return applianceDtoSet;
    }
}
