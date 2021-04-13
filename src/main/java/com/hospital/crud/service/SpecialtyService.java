package com.hospital.crud.service;

import com.hospital.crud.entity.SpecialtyEntity;
import com.hospital.crud.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SpecialtyService {

    final
    SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public List<SpecialtyEntity> list(){
        return specialtyRepository.findAll();
    }
    public Optional<SpecialtyEntity> getById(int id){
        return specialtyRepository.getById(id);
    }
    public List<SpecialtyEntity> findByName(String name){ return specialtyRepository.findAllByNameContains(name); }
    public void  save(SpecialtyEntity data){
        specialtyRepository.save(data);
    }
    public void delete(int id){
        specialtyRepository.deleteById(id);
    }
}
