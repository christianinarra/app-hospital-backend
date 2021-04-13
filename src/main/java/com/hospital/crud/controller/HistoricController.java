package com.hospital.crud.controller;

import com.hospital.crud.dto.DoctorDto;
import com.hospital.crud.dto.HistoricDto;
import com.hospital.crud.dto.Message;
import com.hospital.crud.dto.SearchNameDto;
import com.hospital.crud.entity.DoctorEntity;
import com.hospital.crud.entity.HistoricEntity;
import com.hospital.crud.entity.HospitalEntity;
import com.hospital.crud.entity.PatientEntity;
import com.hospital.crud.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/historic")
@CrossOrigin(origins = "http://localhost:4200")
public class HistoricController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;
    @Autowired
    HistoricService historicService;

    @GetMapping("/list")
    public ResponseEntity<List<HistoricEntity>> list(){
        List<HistoricEntity> list = historicService.list();
        return new ResponseEntity<List<HistoricEntity>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<HistoricEntity> getById(@PathVariable("id") int id) {
        HistoricEntity historicEntity = historicService.getById(id).get();
        return new ResponseEntity<HistoricEntity>(historicEntity, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody HistoricDto historicDto){
        if(StringUtils.isBlank(historicDto.getDescription()))
            return new ResponseEntity(new Message("The Description is required"), HttpStatus.BAD_REQUEST);
        DoctorEntity doctor = doctorService.getById(historicDto.getDoctorId()).get();
        PatientEntity patient = patientService.getById(historicDto.getPatientId()).get();
        HistoricEntity historicEntity = new HistoricEntity();
        historicEntity.setDescription(historicDto.getDescription());
        historicEntity.setDateVisit(historicDto.getDateVisit());
        historicEntity.setDoctor(doctor);
        historicEntity.setPatient(patient);
        historicService.save(historicEntity);
        return new ResponseEntity(new Message("Historic created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody HistoricDto historicDto){
        if(StringUtils.isBlank(historicDto.getDescription()))
            return new ResponseEntity(new Message("The Description is required"), HttpStatus.BAD_REQUEST);

        DoctorEntity doctor = doctorService.getById(historicDto.getDoctorId()).get();
        PatientEntity patient = patientService.getById(historicDto.getPatientId()).get();
        HistoricEntity historicEntity = historicService.getById(id).get();
        historicEntity.setDescription(historicDto.getDescription());
        historicEntity.setDateVisit(historicDto.getDateVisit());
        historicEntity.setDoctor(doctor);
        historicEntity.setPatient(patient);
        historicService.save(historicEntity);
        return new ResponseEntity(new Message("Historic updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id) throws IOException {
        historicService.delete(id);
        return new ResponseEntity(new Message("Doctor deleted"), HttpStatus.OK);
    }
}
