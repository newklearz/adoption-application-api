package com.p5.adoptions.service;
import com.p5.adoptions.repository.cats.Cat;
import com.p5.adoptions.repository.shelters.AnimalShelter;
import com.p5.adoptions.repository.shelters.AnimalShelterRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
    Optional <AnimalShelter> oldShelter = animalShelterRepository.findById(id);
    if(oldShelter.isPresent()){
        animalShelter.setId(id);
        return animalShelterRepository.save(animalShelter);
    }

    throw new EntityNotFoundException("Shelter with id " + id + " not found");


    }

    public void deleteShelter(Integer id) {
        animalShelterRepository.deleteById(id);
    }

    public AnimalShelter findById(Integer id) {

        Optional <AnimalShelter> optional = animalShelterRepository.findById(id);
        return  optional.orElseThrow( () -> new EntityNotFoundException("Shelter with id " + id +" not found"));

    }

    public List <Cat> findAllCatsByShelter(Integer shelterId) {
        Optional<AnimalShelter> optional = animalShelterRepository.findById(shelterId);
        if(optional.isPresent()){
            return optional.get().getCats();
        }
        throw new EntityNotFoundException("Shelter with id " + shelterId + " not found");
    }

    public List<Cat> addNewCatToShelter(Integer shelterId, Cat cat) {
        Optional<AnimalShelter> optional = animalShelterRepository.findById(shelterId);

        if(optional.isPresent()){
            AnimalShelter shelter = optional.get();
            shelter.getCats().add(cat);
            animalShelterRepository.save(shelter);
            return shelter.getCats();
        }

        throw new EntityNotFoundException("Shelter with id " + shelterId + " not found");

    }
}
