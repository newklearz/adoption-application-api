package com.p5.adoptions.service.adapters;

import com.p5.adoptions.repository.shelters.AnimalShelter;
import com.p5.adoptions.service.DTO.ShelterDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ShelterAdapter {
    public static ShelterDTO toDTO (AnimalShelter animalShelter){
        return  new ShelterDTO()
                .setId(animalShelter.getId())
                .setName(animalShelter.getName())
                .setLocation(animalShelter.getLocation())
                .setCats(CatAdapter.toDTOList(animalShelter.getCats()));


    }

    public static AnimalShelter toEntity(ShelterDTO shelterDTO){
        AnimalShelter shelter = new AnimalShelter();
        shelter.setId(shelterDTO.getId());
        shelter.setName(shelterDTO.getName());
        shelter.setLocation(shelterDTO.getLocation());
        shelter.setCats(CatAdapter.toEntityList(shelterDTO.getCats()));
        return  shelter;

    }

    public static List<ShelterDTO> toDTOList(List<AnimalShelter> animalShelters){
        return animalShelters
                .stream()
                .map(ShelterAdapter::toDTO)
                .collect(Collectors.toList());
    }

    public static List<AnimalShelter> toEntityList(List <ShelterDTO> shelterDTOList){

        return shelterDTOList
                .stream()
                .map(ShelterAdapter :: toEntity)
                .collect(Collectors.toList());

    }
}
