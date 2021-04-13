package com.hospital.crud.service;

import com.hospital.crud.entity.HistoricEntity;
import com.hospital.crud.entity.PatientEntity;
import com.hospital.crud.repository.HistoricRepository;
import com.hospital.crud.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistoricService {

    final
    HistoricRepository historicRepository;

    public HistoricService(HistoricRepository historicRepository) {
        this.historicRepository = historicRepository;
    }

    public List<HistoricEntity> list(){
        return historicRepository.findAll();
    }
    public Optional<HistoricEntity> getById(int id){
        return historicRepository.getById(id);
    }
    public void  save(HistoricEntity patient){
        historicRepository.save(patient);
    }
    public void delete(int id){
        historicRepository.deleteById(id);
    }
}
