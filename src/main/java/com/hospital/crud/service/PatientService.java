package com.hospital.crud.service;

import com.hospital.crud.entity.PatientEntity;
import com.hospital.crud.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService {

    final
    PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientEntity> list(){
        return patientRepository.findAll();
    }
    public Optional<PatientEntity> getById(int id){
        return patientRepository.getById(id);
    }
    public List<PatientEntity> findByName(String name){ return patientRepository.findAllByNameContains(name); }
    public void  save(PatientEntity patient){
        patientRepository.save(patient);
    }
    public void delete(int id){
        patientRepository.deleteById(id);
    }
}
