package org.example.rentproxy.mapper;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.dto.PostDto;
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
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class EntityMapper extends Mapper{
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
}
