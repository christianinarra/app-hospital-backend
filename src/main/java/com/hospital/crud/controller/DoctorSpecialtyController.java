package com.hospital.crud.controller;

import com.hospital.crud.entity.DoctorSpecialtyEntity;
import com.hospital.crud.service.DoctorSpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor-specialty")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorSpecialtyController {

    @Autowired
    DoctorSpecialtyService doctorSpecialtyService;

    @GetMapping("/doctor-id/{id}")
    public ResponseEntity<List<DoctorSpecialtyEntity>> getById(@PathVariable("id") int id) {
        List<DoctorSpecialtyEntity> list = doctorSpecialtyService.findSpecialtyByDoctorId(id);
        return new ResponseEntity<List<DoctorSpecialtyEntity>>(list, HttpStatus.OK);
    }
}
