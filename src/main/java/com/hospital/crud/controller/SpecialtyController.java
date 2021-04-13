package com.hospital.crud.controller;

import com.hospital.crud.dto.Message;
import com.hospital.crud.dto.SearchNameDto;
import com.hospital.crud.dto.SpetialtyDto;
import com.hospital.crud.entity.SpecialtyEntity;
import com.hospital.crud.service.CloudinaryService;
import com.hospital.crud.service.SpecialtyService;
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
@RequestMapping("/specialty")
@CrossOrigin(origins = "http://localhost:4200")
public class SpecialtyController {

    @Autowired
    SpecialtyService specialtyService;
    @Autowired
    CloudinaryService cloudinaryService;

    @GetMapping("/list")
    public ResponseEntity<List<SpecialtyEntity>> list(){
        List<SpecialtyEntity> list = specialtyService.list();
        return new ResponseEntity<List<SpecialtyEntity>>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<SpecialtyEntity> getById(@PathVariable("id") int id) {
        SpecialtyEntity specialtyEntity = specialtyService.getById(id).get();
        return new ResponseEntity<SpecialtyEntity>(specialtyEntity, HttpStatus.OK);
    }

    @PostMapping("/search-by-name")
    public ResponseEntity<List<SpecialtyEntity>> searchByName(@RequestBody SearchNameDto searchNameDto){
        List<SpecialtyEntity> list = specialtyService.findByName(searchNameDto.getName());
        return new ResponseEntity<List<SpecialtyEntity>>(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SpetialtyDto spetialtyDto){
        if(StringUtils.isBlank(spetialtyDto.getName()))
            return new ResponseEntity(new Message("The Name is required"), HttpStatus.BAD_REQUEST);

        SpecialtyEntity specialty = new SpecialtyEntity(spetialtyDto.getName(), spetialtyDto.getDescription(), spetialtyDto.getAvatarIcon());
        specialtyService.save(specialty);
        return new ResponseEntity(new Message("Specialty created"), HttpStatus.OK);
    }

    @PostMapping("/upload-photo/{id}")
    public ResponseEntity<?> upload(@PathVariable("id")int id, @RequestParam MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Message("Image not valid"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        SpecialtyEntity specialty = specialtyService.getById(id).get();
        if (specialty.getPublicIdCloudinary() != null){
            cloudinaryService.delete(specialty.getPublicIdCloudinary());
        }
        specialty.setAvatarIcon((String)result.get("url"));
        specialty.setPublicIdCloudinary((String)result.get("public_id"));
        specialtyService.save(specialty);
        return new ResponseEntity(new Message("Specialty uploaded photo"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody SpetialtyDto spetialtyDto){
        if(StringUtils.isBlank(spetialtyDto.getName()))
            return new ResponseEntity(new Message("The Name is required"), HttpStatus.BAD_REQUEST);

        SpecialtyEntity specialty = specialtyService.getById(id).get();
        specialty.setName(spetialtyDto.getName());
        specialty.setDescription(spetialtyDto.getDescription());
        specialty.setAvatarIcon(spetialtyDto.getAvatarIcon());
        specialtyService.save(specialty);
        return new ResponseEntity(new Message("Specialty updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id) throws IOException {
        SpecialtyEntity specialty = specialtyService.getById(id).get();
        if (specialty.getPublicIdCloudinary() != null){
            cloudinaryService.delete(specialty.getPublicIdCloudinary());
        }
        specialtyService.delete(id);
        return new ResponseEntity(new Message("Specialty deleted"), HttpStatus.OK);
    }
}
