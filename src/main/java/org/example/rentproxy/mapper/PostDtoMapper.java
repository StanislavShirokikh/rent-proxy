package org.example.rentproxy.mapper;

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
import org.example.rentproxy.repository.entities.Post;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PostDtoMapper extends Mapper{
    public PostDto convertToPostDto(Post post) {
        UserDto userDto = map(post.getUser(), UserDto.class);

        RentConditionInfoDto rentConditionInfoDto = map(post.getRentConditionInfo(), RentConditionInfoDto.class);
        rentConditionInfoDto.setTypeOfPaymentDto(mapSet(post.getRentConditionInfo().getTypeOfPayment(), TypeOfPaymentDto.class));

        ApartmentInfoDto apartmentInfoDto = map(post.getApartmentInfo(), ApartmentInfoDto.class);
        BathroomTypeDto bathroomTypeDto = map(post.getApartmentInfo().getBathroomType(), BathroomTypeDto.class);
        BalconyTypeDto balconyTypeDto = map(post.getApartmentInfo().getBalconyType(), BalconyTypeDto.class);
        RepairTypeDto repairTypeDto = map(post.getApartmentInfo().getRepairType(), RepairTypeDto.class);
        RoomsTypeDto roomsTypeDto = map(post.getApartmentInfo().getRoomsType(), RoomsTypeDto.class);
        apartmentInfoDto.setBathroomTypeDto(bathroomTypeDto);
        apartmentInfoDto.setRoomsTypeDto(roomsTypeDto);
        apartmentInfoDto.setRepairTypeDto(repairTypeDto);
        apartmentInfoDto.setBalconyTypeDto(balconyTypeDto);
        apartmentInfoDto.setFurnitureDto(mapSet(post.getApartmentInfo().getFurniture(), FurnitureDto.class));
        apartmentInfoDto.setApplianceDto(mapSet(post.getApartmentInfo().getAppliance(), ApplianceDto.class));

        HouseInfoDto houseInfoDto = map(post.getHouseInfo(), HouseInfoDto.class);
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

    public List<PostDto> convertToListPostDto(List<Post> posts) {
        return convertToList(posts, this::convertToPostDto);
    }
}
