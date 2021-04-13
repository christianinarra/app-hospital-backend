package com.hospital.crud.service;

import com.hospital.crud.entity.DoctorEntity;
import com.hospital.crud.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorService {

    final
    DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorEntity> list(){
        return doctorRepository.findAll();
    }
    public Optional<DoctorEntity> getById(int id){
        return doctorRepository.getById(id);
    }
    public List<DoctorEntity> findByName(String name){ return doctorRepository.findAllByNameContains(name); }
    public void  save(DoctorEntity doctor){
        doctorRepository.save(doctor);
    }
    public void delete(int id){
        doctorRepository.deleteById(id);
    }
}
