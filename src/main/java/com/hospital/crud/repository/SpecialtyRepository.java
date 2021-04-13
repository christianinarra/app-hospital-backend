package com.hospital.crud.repository;

import com.hospital.crud.entity.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, Integer> {
    List<SpecialtyEntity> findAllByNameContains(String name);
    Optional<SpecialtyEntity> getById(int id);
}
