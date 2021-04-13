package com.hospital.crud.repository;

import com.hospital.crud.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {
    List<DoctorEntity> findAllByNameContains(String name);
    Optional<DoctorEntity> getById(int id);
}
