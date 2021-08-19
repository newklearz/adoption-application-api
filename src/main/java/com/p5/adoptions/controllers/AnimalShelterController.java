package com.p5.adoptions.controllers;


import com.p5.adoptions.repository.shelters.AnimalShelter;
import com.p5.adoptions.service.AnimalShelterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shelters")
public class AnimalShelterController {

    private final AnimalShelterService animalShelterService;

    public AnimalShelterController(AnimalShelterService animalShelterService) {
        this.animalShelterService = animalShelterService;
    }

    @GetMapping()
    public ResponseEntity<List<AnimalShelter>> getShelters(){
        return ResponseEntity.ok(animalShelterService.findAll());
    }

    @PostMapping()
    public ResponseEntity<AnimalShelter> createShelter(@RequestBody AnimalShelter animalShelter){
        return ResponseEntity.ok(animalShelterService.createShelter(animalShelter));
    }

    @PutMapping("/{id}")
        public ResponseEntity<AnimalShelter> updateShelter(@PathVariable("id") Integer id, @RequestBody AnimalShelter animalShelter){
        return ResponseEntity.ok(animalShelterService.updateShelter(id, animalShelter));
    }

    @DeleteMapping ("/{id}")
    public  ResponseEntity <Object>deleteShelter(@PathVariable ("id") Integer id){
        animalShelterService.deleteShelter(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }


}
