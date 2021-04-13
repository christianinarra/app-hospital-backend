package com.hospital.crud.controller;

import com.hospital.crud.dto.Message;
import com.hospital.crud.dto.PatientDto;
import com.hospital.crud.dto.SearchNameDto;
import com.hospital.crud.entity.PatientEntity;
import com.hospital.crud.entity.SpecialtyEntity;
import com.hospital.crud.service.CloudinaryService;
import com.hospital.crud.service.PatientService;
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
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

    @Autowired
    PatientService patientService;
    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping("/list")
    public ResponseEntity<List<PatientEntity>> list(){
        List<PatientEntity> list = patientService.list();
        return new ResponseEntity<List<PatientEntity>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<PatientEntity> getById(@PathVariable("id") int id) {
        PatientEntity patientEntity = patientService.getById(id).get();
        return new ResponseEntity<PatientEntity>(patientEntity, HttpStatus.OK);
    }

    @PostMapping("/search-by-name")
    public ResponseEntity<List<PatientEntity>> searchByName(@RequestBody SearchNameDto searchNameDto){
        List<PatientEntity> list = patientService.findByName(searchNameDto.getName());
        return new ResponseEntity<List<PatientEntity>>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PatientDto patientDto){
        if(StringUtils.isBlank(patientDto.getName()))
            return new ResponseEntity(new Message("The Name is required"), HttpStatus.BAD_REQUEST);

        PatientEntity patient = new PatientEntity(patientDto.getName(), patientDto.getLastName(), patientDto.getAddress(), patientDto.getDateBirth(), patientDto.getProfilePhoto());
        patientService.save(patient);
        return new ResponseEntity(new Message("Patient created"), HttpStatus.OK);
    }

    @PostMapping("/upload-photo/{id}")
    public ResponseEntity<?> upload(@PathVariable("id")int id, @RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("Image not valid"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        PatientEntity patient = patientService.getById(id).get();
        if (patient.getPublicIdCloudinary() != null){
            cloudinaryService.delete(patient.getPublicIdCloudinary());
        }
        patient.setProfilePhoto((String)result.get("url"));
        patient.setPublicIdCloudinary((String)result.get("public_id"));
        patientService.save(patient);
        return new ResponseEntity(new Message("Patient uploaded photo"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody PatientDto patientDto){
        if(StringUtils.isBlank(patientDto.getName()))
            return new ResponseEntity(new Message("The Name is required"), HttpStatus.BAD_REQUEST);

        PatientEntity patient = patientService.getById(id).get();
        patient.setName(patientDto.getName());
        patient.setLastName(patientDto.getLastName());
        patient.setAddress(patientDto.getAddress());
        patient.setDateBirth(patientDto.getDateBirth());
        patient.setProfilePhoto(patientDto.getProfilePhoto());
        patientService.save(patient);
        return new ResponseEntity(new Message("Patient updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id) throws IOException {
        PatientEntity patient = patientService.getById(id).get();
        if (patient.getPublicIdCloudinary() != null){
            cloudinaryService.delete(patient.getPublicIdCloudinary());
        }
        patientService.delete(id);
        return new ResponseEntity(new Message("Patient deleted"), HttpStatus.OK);
    }
}
