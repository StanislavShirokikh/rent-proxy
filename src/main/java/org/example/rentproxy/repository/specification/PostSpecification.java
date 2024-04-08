package org.example.rentproxy.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.example.rentproxy.repository.entities.ApartmentInfo;
import org.example.rentproxy.repository.entities.Appliance;
import org.example.rentproxy.repository.entities.Furniture;
import org.example.rentproxy.repository.entities.Post;
import org.example.rentproxy.repository.entities.RentConditionInfo;
import org.example.rentproxy.repository.entities.RentType;
import org.example.rentproxy.filter.Filter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PostSpecification implements Specification<Post> {
    private final Filter filter;


    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getRentType() != null) {
            Join<Post, RentType> rentTypeJoin = root.join("rentType", JoinType.INNER);
            predicates.add(criteriaBuilder.equal(rentTypeJoin.get("name"), filter.getRentType()));
        }
        Join<Post, ApartmentInfo> apartmentInfoJoin = root.join("apartmentInfo", JoinType.INNER);
        if (filter.getRoomsCount() != null) {
            predicates.add(criteriaBuilder.equal(apartmentInfoJoin.get("roomsCount"), filter.getRoomsCount()));
        }
        Join<Post, RentConditionInfo> rentConditionInfoJoin= root.join("rentConditionInfo", JoinType.INNER);
        if (filter.getMinPrice() != null) {
            predicates.add(criteriaBuilder.ge(rentConditionInfoJoin.get("price"), filter.getMinPrice()));
        }
        if (filter.getMaxPrice() != null) {
            predicates.add(criteriaBuilder.le(rentConditionInfoJoin.get("price"), filter.getMaxPrice()));
        }
        if (filter.getMinTotalArea() != null) {
            predicates.add(criteriaBuilder.ge(rentConditionInfoJoin.get("total_area"), filter.getMinPrice()));
        }
        if (filter.getMaxTotalArea() != null) {
            predicates.add(criteriaBuilder.le(rentConditionInfoJoin.get("total_area"), filter.getMaxPrice()));
        }
        if (filter.getMinFlour() != null) {
            predicates.add(criteriaBuilder.ge(rentConditionInfoJoin.get("min_floor"), filter.getMinFlour()));
        }
        if (filter.getMaxFlour() != null) {
            predicates.add(criteriaBuilder.le(rentConditionInfoJoin.get("max_floor"), filter.getMaxFlour()));
        }
        if (filter.getFurniture() != null) {
            Join<ApartmentInfo, Furniture> furnitureJoin = apartmentInfoJoin.join("furniture");
            predicates.add(furnitureJoin.get("name").in(filter.getFurniture()));
        }
        if (filter.getAppliance() != null) {
            Join<ApartmentInfo, Appliance> applianceJoin = apartmentInfoJoin.join("appliance");
            predicates.add(applianceJoin.get("name").in(filter.getAppliance()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
