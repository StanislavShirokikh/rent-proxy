package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.RentConditionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentConditionInfoRepository extends JpaRepository<RentConditionInfo, Long> {
    RentConditionInfo findRentConditionInfoByPostId(Long id);
}
