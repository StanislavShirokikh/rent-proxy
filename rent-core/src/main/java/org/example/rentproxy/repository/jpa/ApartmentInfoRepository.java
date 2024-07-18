package org.example.rentproxy.repository.jpa;

import org.example.rentproxy.repository.jpa.entities.ApartmentInfo;
import org.example.rentproxy.repository.jpa.entities.ApartmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.rentproxy.repository.jpa.entities.ApartmentInfo;

@Repository
public interface ApartmentInfoRepository extends JpaRepository<ApartmentInfo, Long> {
    ApartmentInfo findApartmentInfoByPostId(Long postId);
}
