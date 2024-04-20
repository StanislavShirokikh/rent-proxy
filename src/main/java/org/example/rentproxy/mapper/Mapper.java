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
import org.example.rentproxy.response.ApartmentInfoResponse;
import org.example.rentproxy.response.ApplianceResponse;
import org.example.rentproxy.response.BalconyTypeResponse;
import org.example.rentproxy.response.BathroomTypeResponse;
import org.example.rentproxy.response.FurnitureResponse;
import org.example.rentproxy.response.HouseInfoResponse;
import org.example.rentproxy.response.HouseTypeResponse;
import org.example.rentproxy.response.PostResponse;
import org.example.rentproxy.response.RentConditionInfoResponse;
import org.example.rentproxy.response.RentTypeResponse;
import org.example.rentproxy.response.RepairTypeResponse;
import org.example.rentproxy.response.RoomsTypeResponse;
import org.example.rentproxy.response.TypeOfPaymentResponse;
import org.example.rentproxy.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Mapper extends ModelMapper {
    Configuration configuration;
    private Mapper() {
        this.configuration = getConfiguration().setSkipNullEnabled(true);
    }
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
        rentConditionInfo.setTypeOfPayment(mapSet(postDto.getRentConditionInfoDto().getTypeOfPaymentDto(), TypeOfPayment.class));

        ApartmentInfo apartmentInfo = map(postDto.getApartmentInfoDto(), ApartmentInfo.class);
        BathroomType bathroomType = map(postDto.getApartmentInfoDto().getBathroomTypeDto(), BathroomType.class);
        BalconyType balconyType = map(postDto.getApartmentInfoDto().getBalconyTypeDto(), BalconyType.class);
        RepairType repairType = map(postDto.getApartmentInfoDto().getRepairTypeDto(), RepairType.class);
        RoomsType roomsType = map(postDto.getApartmentInfoDto().getRoomsTypeDto(), RoomsType.class);
        apartmentInfo.setBathroomType(bathroomType);
        apartmentInfo.setRoomsType(roomsType);
        apartmentInfo.setRepairType(repairType);
        apartmentInfo.setBalconyType(balconyType);
        apartmentInfo.setFurniture(mapSet(postDto.getApartmentInfoDto().getFurnitureDto(), Furniture.class));
        apartmentInfo.setAppliance(mapSet(postDto.getApartmentInfoDto().getApplianceDto(), Appliance.class));

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

    public PostResponse convertToPostResponse(PostDto postDto) {
        UserResponse userResponse = null;
        if (postDto.getUserDto() != null) {
            userResponse = map(postDto.getUserDto(), UserResponse.class);
        }

        RentConditionInfoResponse rentConditionInfoResponse = map(postDto.getRentConditionInfoDto(),
                RentConditionInfoResponse.class);
        rentConditionInfoResponse.setTypeOfPaymentResponse(mapSet(postDto.getRentConditionInfoDto().getTypeOfPaymentDto(),
                TypeOfPaymentResponse.class));

        ApartmentInfoResponse apartmentInfoResponse = map(postDto.getApartmentInfoDto(), ApartmentInfoResponse.class);
        BathroomTypeResponse bathroomTypeResponse = map(postDto.getApartmentInfoDto().getBathroomTypeDto(),
                BathroomTypeResponse.class);
        BalconyTypeResponse balconyTypeResponse = map(postDto.getApartmentInfoDto().getBalconyTypeDto(),
                BalconyTypeResponse.class);
        RepairTypeResponse repairTypeResponse = map(postDto.getApartmentInfoDto().getRepairTypeDto(),
                RepairTypeResponse.class);
        RoomsTypeResponse roomsType = map(postDto.getApartmentInfoDto().getRoomsTypeDto(), RoomsTypeResponse.class);
        apartmentInfoResponse.setBathroomTypeResponse(bathroomTypeResponse);
        apartmentInfoResponse.setRoomsTypeResponse(roomsType);
        apartmentInfoResponse.setRepairTypeResponse(repairTypeResponse);
        apartmentInfoResponse.setBalconyTypeResponse(balconyTypeResponse);
        apartmentInfoResponse.setFurnitureResponse(mapSet(postDto.getApartmentInfoDto().getFurnitureDto(), FurnitureResponse.class));
        apartmentInfoResponse.setApplianceResponse(mapSet(postDto.getApartmentInfoDto().getApplianceDto(), ApplianceResponse.class));

        HouseInfoResponse houseInfoResponse = map(postDto.getHouseInfoDto(), HouseInfoResponse.class);
        HouseTypeResponse houseTypeResponse = map(postDto.getHouseInfoDto().getHouseTypeDto(), HouseTypeResponse.class);
        houseInfoResponse.setHouseTypeResponse(houseTypeResponse);

        RentTypeResponse rentTypeResponse = map(postDto.getRentTypeDto(), RentTypeResponse.class);

        PostResponse postResponse = map(postDto, PostResponse.class);
        postResponse.setUserResponse(userResponse);
        postResponse.setRentConditionInfoResponse(rentConditionInfoResponse);
        postResponse.setApartmentInfoResponse(apartmentInfoResponse);
        postResponse.setHouseInfoResponse(houseInfoResponse);
        postResponse.setRentTypeResponse(rentTypeResponse);

        return postResponse;
    }

    public List<PostDto> convertToListPostDto(List<Post> posts) {
        return convertToList(posts, this::convertToPostDto);
    }

    public List<PostResponse> convertToListResponse(List<PostDto> posts) {
        return convertToList(posts, this::convertToPostResponse);
    }

    private <S, T> Set<T> mapSet(Set<S> sourceSet, Class<T> targetClass) {
        return sourceSet.stream()
                .map(source -> this.map(source, targetClass))
                .collect(Collectors.toSet());
    }

    private  <S, T> List<T> convertToList(List<S> sourceList, Function<S, T> mapper) {
        return sourceList.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
