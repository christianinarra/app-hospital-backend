package com.hospital.crud.controller;

import com.hospital.crud.dto.DoctorDto;
import com.hospital.crud.dto.Message;
import com.hospital.crud.dto.SearchNameDto;
import com.hospital.crud.entity.DoctorEntity;
import com.hospital.crud.entity.HospitalEntity;
import com.hospital.crud.service.CloudinaryService;
import com.hospital.crud.service.DoctorService;
import com.hospital.crud.service.HospitalService;
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
@RequestMapping("/doctor")
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {

    @Autowired
    DoctorService doctorService;
    @Autowired
    HospitalService hospitalService;
    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping("/list")
    public ResponseEntity<List<DoctorEntity>> list(){
        List<DoctorEntity> list = doctorService.list();
        return new ResponseEntity<List<DoctorEntity>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DoctorEntity> getById(@PathVariable("id") int id) {
        DoctorEntity DoctorEntity = doctorService.getById(id).get();
        return new ResponseEntity<DoctorEntity>(DoctorEntity, HttpStatus.OK);
    }

    @PostMapping("/search-by-name")
    public ResponseEntity<List<DoctorEntity>> searchByName(@RequestBody SearchNameDto searchNameDto){
        List<DoctorEntity> list = doctorService.findByName(searchNameDto.getName());
        return new ResponseEntity<List<DoctorEntity>>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DoctorDto doctorDto){
        if(StringUtils.isBlank(doctorDto.getName()))
            return new ResponseEntity(new Message("The Name is required"), HttpStatus.BAD_REQUEST);
        HospitalEntity hospital = hospitalService.getById(doctorDto.getHospitalId()).get();
        DoctorEntity doctor = new DoctorEntity(doctorDto.getName(), doctorDto.getLastName(), doctorDto.getAddress(), doctorDto.getDateBirth(), doctorDto.getProfilePhoto(), hospital);
        doctorService.save(doctor);
        return new ResponseEntity(new Message("Doctor created"), HttpStatus.OK);
    }

    @PostMapping("/upload-photo/{id}")
    public ResponseEntity<?> upload(@PathVariable("id")int id, @RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("Image not valid"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        DoctorEntity doctor = doctorService.getById(id).get();
        if (doctor.getPublicIdCloudinary() != null){
            cloudinaryService.delete(doctor.getPublicIdCloudinary());
        }
        doctor.setProfilePhoto((String)result.get("url"));
        doctor.setPublicIdCloudinary((String)result.get("public_id"));
        doctorService.save(doctor);
        return new ResponseEntity(new Message("Doctor uploaded photo"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody DoctorDto doctorDto){
        if(StringUtils.isBlank(doctorDto.getName()))
            return new ResponseEntity(new Message("The Name is required"), HttpStatus.BAD_REQUEST);

        HospitalEntity hospital = hospitalService.getById(doctorDto.getHospitalId()).get();
        DoctorEntity doctor = doctorService.getById(id).get();
        doctor.setName(doctorDto.getName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setAddress(doctorDto.getAddress());
        doctor.setDateBirth(doctorDto.getDateBirth());
        doctor.setProfilePhoto(doctorDto.getProfilePhoto());
        doctor.setHospital(hospital);
        doctorService.save(doctor);
        return new ResponseEntity(new Message("Doctor updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id) throws IOException {
        DoctorEntity doctor = doctorService.getById(id).get();
        if (doctor.getPublicIdCloudinary() != null){
            cloudinaryService.delete(doctor.getPublicIdCloudinary());
        }
        doctorService.delete(id);
        return new ResponseEntity(new Message("Doctor deleted"), HttpStatus.OK);
    }
}
