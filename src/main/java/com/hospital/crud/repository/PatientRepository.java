package com.hospital.crud.repository;

import com.hospital.crud.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {
    List<PatientEntity> findAllByNameContains(String name);
    Optional<PatientEntity> getById(int id);
}
