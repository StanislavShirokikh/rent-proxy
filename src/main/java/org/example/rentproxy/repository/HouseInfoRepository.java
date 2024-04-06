package org.example.rentproxy.repository;

import org.example.rentproxy.repository.entities.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseInfoRepository extends JpaRepository<HouseInfo, Long> {
    HouseInfo findHouseInfoByPostId(Long postId);
}
