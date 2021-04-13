package com.hospital.crud.repository;

import com.hospital.crud.entity.DoctorSpecialtyEntity;
import com.hospital.crud.entity.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorSpecialtyRepository extends JpaRepository<DoctorSpecialtyEntity, Integer> {
    List<DoctorSpecialtyEntity> findAllByDoctorId(int id);
}
