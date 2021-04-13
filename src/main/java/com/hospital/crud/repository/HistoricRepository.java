package com.hospital.crud.repository;

import com.hospital.crud.entity.HistoricEntity;
import com.hospital.crud.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoricRepository extends JpaRepository<HistoricEntity, Integer> {
    Optional<HistoricEntity> getById(int id);
}
