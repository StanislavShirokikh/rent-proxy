package org.example.rentproxy.repository.jpa;

import lombok.RequiredArgsConstructor;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.filter.PostOrder;
import org.example.rentproxy.repository.jpa.entities.Appliance;
import org.example.rentproxy.repository.jpa.entities.Furniture;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.TypeOfPayment;
import org.example.rentproxy.repository.jpa.specification.PostSpecification;
import org.example.rentproxy.repository.jpa.entities.Appliance;
import org.example.rentproxy.repository.jpa.entities.Furniture;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.TypeOfPayment;
import org.example.rentproxy.filter.Filter;
import org.example.rentproxy.filter.PostOrder;
import org.example.rentproxy.repository.jpa.specification.PostSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.example.rentproxy.repository.jpa.entities.Appliance;
import org.example.rentproxy.repository.jpa.entities.Furniture;
import org.example.rentproxy.repository.jpa.entities.Post;
import org.example.rentproxy.repository.jpa.entities.TypeOfPayment;
import org.example.rentproxy.repository.jpa.specification.PostSpecification;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{
    private final PostJpaRepository postJpaRepository;
    private final UserRepository userRepository;
    private final ApplianceRepository applianceRepository;
    private final BalconyTypeRepository balconyTypeRepository;
    private final BathroomTypeRepository bathroomTypeRepository;
    private final FurnitureRepository furnitureRepository;
    private final HouseTypeRepository houseTypeRepository;
    private final RentTypeRepository rentTypeRepository;
    private final RepairTypeRepository repairTypeRepository;
    private final RoomsTypeRepository roomsTypeRepository;
    private final TypeOfPaymentRepository typeOfPaymentRepository;

    @Override
    public Post save(Post post) {
        post.setUser(userRepository.findByLogin(post.getUser().getLogin()));

        post.getRentConditionInfo().setPost(post);
        Set<TypeOfPayment> fetchedTypes = typeOfPaymentRepository.findByNameIn(
                post.getRentConditionInfo().getTypeOfPayment().stream()
                        .map(TypeOfPayment::getName)
                        .collect(Collectors.toSet())
        );
        post.getRentConditionInfo().setTypeOfPayment(fetchedTypes);

        post.getApartmentInfo().setPost(post);
        updateApartmentInfoFromPost(post, post);

        post.getHouseInfo()
                .setHouseType(houseTypeRepository
                        .findByName(post.getHouseInfo()
                                .getHouseType()
                                .getName()));
        post.getHouseInfo().setPost(post);

        post.setRentType(rentTypeRepository.findByName(post.getRentType().getName()));

        return postJpaRepository.save(post);
    }

    @Override
    public void deletePostById(long id) {
        postJpaRepository.deleteById(id);
    }

    @Override
    public String findUserLoginByPostId(Long id) {
        return postJpaRepository.findUserLoginByPostId(id);
    }

    @Override
    public Post findPostById(long id) {
        return postJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> findPostByFilter(Filter filter) {
        return postJpaRepository.findAll(
                new PostSpecification(filter),
                getSortWithPagination(
                        filter.getPostOrder(),
                        filter.getPageNumber(),
                        filter.getPageSize())
                )
                .getContent();
    }

    @Override
    public Post updatePost(Post post) {
        Post foundPost = findPostById(post.getId());
        foundPost.setName(post.getName());
        foundPost.setTitle(post.getTitle());
        foundPost.setDate(post.getDate());

        Set<TypeOfPayment> fetchedTypes = typeOfPaymentRepository.findByNameIn(
                post.getRentConditionInfo().getTypeOfPayment().stream()
                        .map(TypeOfPayment::getName)
                        .collect(Collectors.toSet()));
        foundPost.getRentConditionInfo().setTypeOfPayment(fetchedTypes);
        foundPost.getRentConditionInfo().setDeposit(post.getRentConditionInfo().getDeposit());
        foundPost.getRentConditionInfo().setCommissionPercent(post.getRentConditionInfo().getCommissionPercent());
        foundPost.getRentConditionInfo().setPrice(post.getRentConditionInfo().getPrice());
        foundPost.getRentConditionInfo().setCurrency(post.getRentConditionInfo().getCurrency());

        updateApartmentInfoFromPost(foundPost, post);

        foundPost.getApartmentInfo().setRoomsCount(post.getApartmentInfo().getRoomsCount());
        foundPost.getApartmentInfo().setTotalArea(post.getApartmentInfo().getTotalArea());
        foundPost.getApartmentInfo().setKitchenArea(post.getApartmentInfo().getKitchenArea());
        foundPost.getApartmentInfo().setLivingSpace(post.getApartmentInfo().getLivingSpace());
        foundPost.getApartmentInfo().setFlour(post.getApartmentInfo().getFlour());
        foundPost.getApartmentInfo().setAdditionally(post.getApartmentInfo().getAdditionally());


        foundPost.getHouseInfo()
                .setHouseType(houseTypeRepository
                        .findByName(post.getHouseInfo()
                                .getHouseType()
                                .getName()));
        foundPost.getHouseInfo().setAddress(post.getHouseInfo().getAddress());
        foundPost.getHouseInfo().setFloursCount(post.getHouseInfo().getFloursCount());

        foundPost.setRentType(rentTypeRepository.findByName(post.getRentType().getName()));
        foundPost.setName(foundPost.getName());
        foundPost.setTitle(foundPost.getTitle());
        foundPost.setDate(foundPost.getDate());

        return postJpaRepository.save(foundPost);
    }



    private void updateApartmentInfoFromPost(Post post1, Post post2) {
        post1.getApartmentInfo()
                .setBathroomType(bathroomTypeRepository
                        .findByName(post2.getApartmentInfo()
                                .getBathroomType()
                                .getName()));

        post1.getApartmentInfo()
                .setRepairType(repairTypeRepository
                        .findByName(post2.getApartmentInfo()
                                .getRepairType()
                                .getName()));
        post1.getApartmentInfo()
                .setBalconyType(balconyTypeRepository
                        .findByName(post2.getApartmentInfo()
                                .getBalconyType()
                                .getName()));

        post1.getApartmentInfo()
                .setRoomsType(roomsTypeRepository
                        .findByName(post2.getApartmentInfo()
                                .getRoomsType()
                                .getName()));

        Set<Furniture> fetchedFurniture = furnitureRepository.findByNameIn(
                post2.getApartmentInfo().getFurniture().stream()
                        .map(Furniture::getName)
                        .collect(Collectors.toSet()));
        post1.getApartmentInfo().setFurniture(fetchedFurniture);

        Set<Appliance> fetchedAppliance = applianceRepository.findByNameIn(
                post2.getApartmentInfo().getAppliance().stream()
                        .map(Appliance::getName)
                        .collect(Collectors.toSet()));
        post1.getApartmentInfo().setAppliance(fetchedAppliance);
    }
    private Pageable getSortWithPagination(PostOrder postOrder, Integer pageNumber, Integer pageSize) {
        Pageable pageable = null;

        if (postOrder == PostOrder.ASCENDING_PRICE) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, postOrder.getFieldName()));
        }
        if (postOrder == PostOrder.DESCENDING_PRICE) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, postOrder.getFieldName()));
        }
        if (postOrder == PostOrder.ASCENDING_DATE) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, postOrder.getFieldName()));
        }
        if (postOrder == PostOrder.DESCENDING_DATE) {
            pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, postOrder.getFieldName()));
        }

        return pageable;
    }
}
