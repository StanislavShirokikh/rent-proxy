package org.example.rentproxy.repository;

import org.example.rentproxy.entities.RentConditionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentConditionInfoRepository extends JpaRepository<RentConditionInfo, Long> {
    RentConditionInfo findRentConditionInfoByPostId(Long id);
}
