package com.hospital.crud.service;

import com.hospital.crud.entity.DoctorSpecialtyEntity;
import com.hospital.crud.repository.DoctorSpecialtyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorSpecialtyService {

    final
    DoctorSpecialtyRepository doctorSpecialtyRepository;

    public DoctorSpecialtyService(DoctorSpecialtyRepository doctorSpecialtyRepository) {
        this.doctorSpecialtyRepository = doctorSpecialtyRepository;
    }
    public List<DoctorSpecialtyEntity> findSpecialtyByDoctorId(int id){ return doctorSpecialtyRepository.findAllByDoctorId(id); }
}
