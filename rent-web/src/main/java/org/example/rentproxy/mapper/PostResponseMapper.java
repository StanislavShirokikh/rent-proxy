package org.example.rentproxy.mapper;

import lombok.RequiredArgsConstructor;
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
import org.springframework.stereotype.Component;
import org.example.rentproxy.dto.PostDto;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PostResponseMapper extends Mapper{
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

    public List<PostResponse> convertToListResponse(List<PostDto> posts) {
        return convertToList(posts, this::convertToPostResponse);
    }
}
