package com.p5.adoptions.service;





import com.p5.adoptions.repository.shelters.AnimalShelter;
import com.p5.adoptions.repository.shelters.AnimalShelterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalShelterService
{
    private final AnimalShelterRepository animalShelterRepository;


    public AnimalShelterService(AnimalShelterRepository animalShelterRepository) {
        this.animalShelterRepository = animalShelterRepository;
    }

    public List<AnimalShelter> findAll() {

        return animalShelterRepository.findAll();
    }

    public AnimalShelter createShelter(AnimalShelter animalShelter) {
        return animalShelterRepository.save(animalShelter);
    }

    public AnimalShelter updateShelter(Integer id, AnimalShelter animalShelter) {

        return animalShelterRepository.save(animalShelter);

    }

    public void deleteShelter(Integer id) {
        animalShelterRepository.deleteById(id);
    }
}
