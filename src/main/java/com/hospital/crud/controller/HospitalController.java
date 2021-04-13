package com.hospital.crud.controller;

import com.hospital.crud.dto.HospitalDto;
import com.hospital.crud.dto.Message;
import com.hospital.crud.dto.SearchNameDto;
import com.hospital.crud.entity.HospitalEntity;
import com.hospital.crud.service.HospitalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
@CrossOrigin(origins = "http://localhost:4200")
public class HospitalController {

    @Autowired
    HospitalService hospitalService;

    @GetMapping("/list")
    public ResponseEntity<List<HospitalEntity>> list(){
        List<HospitalEntity> list = hospitalService.list();
        return new ResponseEntity<List<HospitalEntity>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<HospitalEntity> getById(@PathVariable("id") int id) {
        HospitalEntity hospitalEntity = hospitalService.getById(id).get();
        return new ResponseEntity<HospitalEntity>(hospitalEntity, HttpStatus.OK);
    }

    @PostMapping("/search-by-name")
    public ResponseEntity<List<HospitalEntity>> searchByName(@RequestBody SearchNameDto searchNameDto){
        List<HospitalEntity> list = hospitalService.findByName(searchNameDto.getName());
        return new ResponseEntity<List<HospitalEntity>>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody HospitalDto hospitalDto){
        if(StringUtils.isBlank(hospitalDto.getName()))
            return new ResponseEntity(new Message("The Name is required"), HttpStatus.BAD_REQUEST);

        HospitalEntity hospital = new HospitalEntity(hospitalDto.getName(), hospitalDto.getAddress());
        hospitalService.save(hospital);
        return new ResponseEntity(new Message("Hospital created"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody HospitalDto hospitalDto){
        if(StringUtils.isBlank(hospitalDto.getName()))
            return new ResponseEntity(new Message("The Name is required"), HttpStatus.BAD_REQUEST);

        HospitalEntity hospital = hospitalService.getById(id).get();
        hospital.setName(hospitalDto.getName());
        hospital.setAddress(hospitalDto.getAddress());
        hospitalService.save(hospital);
        return new ResponseEntity(new Message("Hospital updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        hospitalService.delete(id);
        return new ResponseEntity(new Message("Hospital deleted"), HttpStatus.OK);
    }
}
