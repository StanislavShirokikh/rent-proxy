package org.example.rentproxy.mapper;

import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.repository.jpa.entities.ApartmentInfo;
import org.example.rentproxy.repository.jpa.entities.Appliance;
import org.example.rentproxy.repository.jpa.entities.BalconyType;
import org.example.rentproxy.repository.jpa.entities.BathroomType;
import org.example.rentproxy.repository.jpa.entities.Furniture;
import org.example.rentproxy.repository.jpa.entities.HouseInfo;
import org.example.rentproxy.repository.jpa.entities.HouseType;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.RentConditionInfo;
import org.example.rentproxy.repository.jpa.entities.RentType;
import org.example.rentproxy.repository.jpa.entities.RepairType;
import org.example.rentproxy.repository.jpa.entities.RoomsType;
import org.example.rentproxy.repository.jpa.entities.TypeOfPayment;
import org.example.rentproxy.repository.jpa.entities.User;
import org.springframework.stereotype.Component;

@Component
public class PostMapper extends Mapper{
    public Post convertToPost(PostDto postDto) {
        User user = null;
        if (postDto.getUserDto() != null) {
            user = map(postDto.getUserDto(), User.class);
        }

        RentConditionInfo rentConditionInfo = map(postDto.getRentConditionInfoDto(), RentConditionInfo.class);
        rentConditionInfo.setTypeOfPayment(mapSet(postDto.getRentConditionInfoDto().getTypeOfPaymentDto(), TypeOfPayment.class));

        ApartmentInfo apartmentInfo = map(postDto.getApartmentInfoDto(), ApartmentInfo.class);
        BathroomType bathroomType = null;
        if (postDto.getApartmentInfoDto().getBathroomTypeDto() != null) {
            bathroomType = map(postDto.getApartmentInfoDto().getBathroomTypeDto(), BathroomType.class);
        }
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
