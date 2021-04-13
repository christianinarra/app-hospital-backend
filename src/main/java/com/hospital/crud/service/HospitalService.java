package com.hospital.crud.service;

import com.hospital.crud.entity.HospitalEntity;
import com.hospital.crud.repository.HospitalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HospitalService {

    final
    HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<HospitalEntity> list(){
        return hospitalRepository.findAll();
    }
    public Optional<HospitalEntity> getById(int id){
        return hospitalRepository.getById(id);
    }
    public List<HospitalEntity> findByName(String name){ return hospitalRepository.findAllByNameContains(name); }
    public void  save(HospitalEntity hospital){
        hospitalRepository.save(hospital);
    }
    public void delete(int id){
        hospitalRepository.deleteById(id);
    }
}
