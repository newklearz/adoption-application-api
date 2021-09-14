package com.p5.adoptions.controllers;





import com.p5.adoptions.service.AnimalShelterService;
import com.p5.adoptions.service.DTO.CatDTO;
import com.p5.adoptions.service.DTO.ListDTO;
import com.p5.adoptions.service.DTO.ShelterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shelters")
public class AnimalShelterController {

    private final AnimalShelterService animalShelterService;

    public AnimalShelterController(AnimalShelterService animalShelterService) {
        this.animalShelterService = animalShelterService;
    }

    @GetMapping()
    public ResponseEntity<ListDTO<ShelterDTO>> getShelters(){
        return ResponseEntity.ok(animalShelterService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelterDTO> getShelter (@PathVariable("id") Integer id){
        return  ResponseEntity.ok(animalShelterService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<ShelterDTO> createShelter(@Valid @RequestBody ShelterDTO shelterDTO){
        return ResponseEntity.ok(animalShelterService.createShelter(shelterDTO));
    }

    @PutMapping("/{id}")
        public ResponseEntity<ShelterDTO> updateShelter( @PathVariable("id") Integer id,@Valid @RequestBody ShelterDTO animalShelter){
        return ResponseEntity.ok(animalShelterService.updateShelter(id, animalShelter));
    }

    @DeleteMapping ("/{id}")
    public  ResponseEntity <Object>deleteShelter(@PathVariable ("id") Integer id){
        animalShelterService.deleteShelter(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @GetMapping ("/{shelterId}/cats")
    public ResponseEntity<List<CatDTO>> getCatsForShelter(@PathVariable ("shelterId") Integer shelterId){

        return ResponseEntity.ok(animalShelterService.findAllCatsByShelter(shelterId));
    }

    @PutMapping("/{shelterId}/cats")
    public ResponseEntity<List<CatDTO>> addNewCatToShelter (@PathVariable("shelterId") Integer shelterId, @RequestBody CatDTO cat){
        return ResponseEntity.ok(animalShelterService.addNewCatToShelter(shelterId, cat));
    }



}
