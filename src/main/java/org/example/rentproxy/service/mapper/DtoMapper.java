package org.example.rentproxy.service.mapper;

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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DtoMapper extends ModelMapper {
    public PostDto convertToPostDto(Post post) {
        UserDto userDto = map(post.getUser(), UserDto.class);

        RentConditionInfoDto rentConditionInfoDto = map(post.getRentConditionInfo(), RentConditionInfoDto.class);
        Set<TypeOfPaymentDto> typeOfPaymentDtoSet = mapTypeOfPaymentSet(post.getRentConditionInfo().getTypeOfPayment());
        rentConditionInfoDto.setTypeOfPaymentDto(typeOfPaymentDtoSet);

        ApartmentInfoDto apartmentInfoDto = map(post.getApartmentInfo(), ApartmentInfoDto.class);
        BathroomTypeDto bathroomTypeDto = map(post.getApartmentInfo().getBathroomType(), BathroomTypeDto.class);
        BalconyTypeDto balconyTypeDto = map(post.getApartmentInfo().getBalconyType(), BalconyTypeDto.class);
        RepairTypeDto repairTypeDto = map(post.getApartmentInfo().getRepairType(), RepairTypeDto.class);
        RoomsTypeDto roomsTypeDto = map(post.getApartmentInfo().getRoomsType(), RoomsTypeDto.class);
        Set<FurnitureDto> furnitureDtoSet = mapFurnitureSet(post.getApartmentInfo().getFurniture());
        Set<ApplianceDto> applianceDtoSet = mapApplianceSet(post.getApartmentInfo().getAppliance());
        apartmentInfoDto.setBathroomTypeDto(bathroomTypeDto);
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

     public Post convertToPost(PostDto postDto) {
        User user = null;
        if (postDto.getUserDto() != null) {
            user = map(postDto.getUserDto(), User.class);
        }

        RentConditionInfo rentConditionInfo = map(postDto.getRentConditionInfoDto(), RentConditionInfo.class);
        Set<TypeOfPayment> typeOfPaymentSet = mapTypeOfPaymentDtoSet(postDto.getRentConditionInfoDto().getTypeOfPaymentDto());
        rentConditionInfo.setTypeOfPayment(typeOfPaymentSet);

        ApartmentInfo apartmentInfo = map(postDto.getApartmentInfoDto(), ApartmentInfo.class);
        BathroomType bathroomType = map(postDto.getApartmentInfoDto().getBathroomTypeDto(), BathroomType.class);
        BalconyType balconyType = map(postDto.getApartmentInfoDto().getBalconyTypeDto(), BalconyType.class);
        RepairType repairType = map(postDto.getApartmentInfoDto().getRepairTypeDto(), RepairType.class);
        RoomsType roomsType = map(postDto.getApartmentInfoDto().getRoomsTypeDto(), RoomsType.class);
        Set<Furniture> furnitureSet = mapFurnitureDtoSet(postDto.getApartmentInfoDto().getFurnitureDto());
        Set<Appliance> applianceSet = mapApplianceDtoSet(postDto.getApartmentInfoDto().getApplianceDto());
        apartmentInfo.setBathroomType(bathroomType);
        apartmentInfo.setRoomsType(roomsType);
        apartmentInfo.setRepairType(repairType);
        apartmentInfo.setBalconyType(balconyType);
        apartmentInfo.setFurniture(furnitureSet);
        apartmentInfo.setAppliance(applianceSet);

        HouseInfo houseInfo = map(postDto.getHouseInfoDto(), HouseInfo.class);
        HouseType houseType = map(postDto.getHouseInfoDto().getHouseTypeDto(), HouseType.class);
        houseInfo.setHouseType(houseType);

        RentType rentType = map(postDto.getRentTypeDto(), RentType.class);

        Post post = map(postDto, Post.class);
        post.setUser(user);
        post.setRentConditionInfo(rentConditionInfo);
        post.setApartmentInfo(apartmentInfo);
        post.setHouseInfo(houseInfo);
        post.setRentType(rentType);

        return post;
    }

    public List<PostDto> convertToListPostDto(List<Post> posts) {
        List<PostDto> postDtoList = new ArrayList<>();
        posts.forEach(post -> postDtoList.add(convertToPostDto(post)));
        return postDtoList;
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

    private Set<TypeOfPayment> mapTypeOfPaymentDtoSet(Set<TypeOfPaymentDto> typeOfPaymentDtoSet) {
        Set<TypeOfPayment> typeOfPaymentSet = new HashSet<>();
        for (TypeOfPaymentDto typeOfPaymentDto : typeOfPaymentDtoSet) {
            typeOfPaymentSet.add(map(typeOfPaymentDto, TypeOfPayment.class));
        }
        return typeOfPaymentSet;
    }

    private Set<Furniture> mapFurnitureDtoSet(Set<FurnitureDto> furnitureDtoSet) {
        Set<Furniture> furnitureSet = new HashSet<>();
        for (FurnitureDto furnitureDto : furnitureDtoSet) {
            furnitureSet.add(map(furnitureDto, Furniture.class));
        }
        return furnitureSet;
    }

    private Set<Appliance> mapApplianceDtoSet(Set<ApplianceDto> applianceDtoSet) {
        Set<Appliance> applianceSet = new HashSet<>();
        for (ApplianceDto applianceDto : applianceDtoSet) {
            applianceSet.add(map(applianceDto, Appliance.class));
        }
        return applianceSet;
    }
}
