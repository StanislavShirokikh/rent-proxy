package org.example.rentproxy.service.mapper;

import org.example.rentproxy.dto.ApartmentInfoDto;
import org.example.rentproxy.dto.ApplianceDto;
import org.example.rentproxy.dto.ArchiveDto;
import org.example.rentproxy.dto.BalconyTypeDto;
import org.example.rentproxy.dto.BathroomTypeDto;
import org.example.rentproxy.dto.FurnitureDto;
import org.example.rentproxy.dto.HouseInfoDto;
import org.example.rentproxy.dto.HouseTypeDto;
import org.example.rentproxy.dto.PostDto;
import org.example.rentproxy.dto.RentConditionInfoDto;
import org.example.rentproxy.dto.RentTypeDto;
import org.example.rentproxy.dto.RepairTypeDto;
import org.example.rentproxy.dto.ReservationRequestDto;
import org.example.rentproxy.dto.RoomsTypeDto;
import org.example.rentproxy.dto.TypeOfPaymentDto;
import org.example.rentproxy.dto.UserDto;
import org.example.rentproxy.repository.entities.ApartmentInfo;
import org.example.rentproxy.repository.entities.Appliance;
import org.example.rentproxy.repository.entities.Archive;
import org.example.rentproxy.repository.entities.BalconyType;
import org.example.rentproxy.repository.entities.BathroomType;
import org.example.rentproxy.repository.entities.Furniture;
import org.example.rentproxy.repository.entities.HouseInfo;
import org.example.rentproxy.repository.entities.HouseType;
import org.example.rentproxy.repository.entities.Post;
import org.example.rentproxy.repository.entities.RentConditionInfo;
import org.example.rentproxy.repository.entities.RentType;
import org.example.rentproxy.repository.entities.RepairType;
import org.example.rentproxy.repository.entities.ReservationRequest;
import org.example.rentproxy.repository.entities.RoomsType;
import org.example.rentproxy.repository.entities.TypeOfPayment;
import org.example.rentproxy.repository.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DtoMapper extends ModelMapper {
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

    public List<PostDto> convertToListPostDto(List<Post> posts) {
        return posts.stream()
                .map(this::convertToPostDto)
                .collect(Collectors.toList());
    }

    private <S, T> Set<T> mapSet(Set<S> sourceSet, Class<T> targetClass) {
        return sourceSet.stream()
                .map(source -> this.map(source, targetClass))
                .collect(Collectors.toSet());
    }

    public ReservationRequest convertToReservationRequest(ReservationRequestDto reservationRequestDto) {
        User user = map(reservationRequestDto.getUserDto(), User.class);
        Post post = map(reservationRequestDto.getPostDto(), Post.class);
        ReservationRequest reservationRequest = map(reservationRequestDto, ReservationRequest.class);
        reservationRequest.setUser(user);
        reservationRequest.setPost(post);

        return reservationRequest;
    }

    public ReservationRequestDto convertToReservationRequestDto(ReservationRequest reservationRequest) {
        UserDto userDto = map(reservationRequest.getUser(), UserDto.class);
        PostDto postDto = map(reservationRequest.getPost(), PostDto.class);
        ReservationRequestDto reservationRequestDto = map(reservationRequest, ReservationRequestDto.class);
        reservationRequestDto.setUserDto(userDto);
        reservationRequestDto.setPostDto(postDto);

        return reservationRequestDto;
    }

    public Archive convertToArchive(ArchiveDto archiveDto) {
        Archive archive = map(archiveDto, Archive.class);
        ReservationRequest reservationRequest = map(archiveDto.getReservationRequestDto(), ReservationRequest.class);
        archive.setReservationRequest(reservationRequest);

        return archive;
    }

    public ArchiveDto convertToArchiveDto(Archive archive) {
        ArchiveDto archiveDto = map(archive, ArchiveDto.class);
        ReservationRequestDto reservationRequestDto = map(archive.getReservationRequest(), ReservationRequestDto.class);
        archiveDto.setReservationRequestDto(reservationRequestDto);

        return archiveDto;
    }
}
