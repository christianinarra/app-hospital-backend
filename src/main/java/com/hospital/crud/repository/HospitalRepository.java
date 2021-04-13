package com.hospital.crud.repository;

import com.hospital.crud.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity, Integer> {
    List<HospitalEntity> findAllByNameContains(String name);
    Optional<HospitalEntity> getById(int id);
}
