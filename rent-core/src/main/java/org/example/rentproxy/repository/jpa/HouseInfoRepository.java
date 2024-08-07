package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.HouseInfo;
import org.example.rentproxy.repository.jpa.entities.HouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.rentproxy.repository.jpa.entities.HouseInfo;

@Repository
public interface HouseInfoRepository extends JpaRepository<HouseInfo, Long> {
    HouseInfo findHouseInfoByPostId(Long postId);
}
