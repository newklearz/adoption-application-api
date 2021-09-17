package com.p5.adoptions.service;

import com.p5.adoptions.repository.shelters.AnimalShelter;
import com.p5.adoptions.repository.shelters.AnimalShelterRepository;
import com.p5.adoptions.service.DTO.CatDTO;
import com.p5.adoptions.service.DTO.ListDTO;
import com.p5.adoptions.service.DTO.ShelterDTO;
import com.p5.adoptions.service.adapters.CatAdapter;
import com.p5.adoptions.service.adapters.ShelterAdapter;
import com.p5.adoptions.service.exceptions.ApiError;
import com.p5.adoptions.service.exceptions.ShelterLocationException;
import com.p5.adoptions.service.exceptions.ValidationException;
import com.p5.adoptions.service.exceptions.Violation;
import com.p5.adoptions.service.validations.OnCreate;
import com.p5.adoptions.service.validations.OnUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Validated
public class AnimalShelterService {
    private final AnimalShelterRepository animalShelterRepository;


    public AnimalShelterService(AnimalShelterRepository animalShelterRepository) {
        this.animalShelterRepository = animalShelterRepository;
    }

    public ListDTO<ShelterDTO> findAll() {
        List<ShelterDTO> data = ShelterAdapter.toDTOList(animalShelterRepository.findAll());
        Long totalCount = animalShelterRepository.count();

        ListDTO<ShelterDTO> response = new ListDTO<>();
        response.setData(data);
        response.setTotalCount(totalCount);

        return response;

    }

    @Validated(OnCreate.class)
    public ShelterDTO createShelter(@Valid ShelterDTO animalShelter) {
        validateShelterLocation(animalShelter);

        AnimalShelter shelter = ShelterAdapter.toEntity(animalShelter);
        AnimalShelter savedShelter = animalShelterRepository.save(shelter);
        return ShelterAdapter.toDTO(savedShelter);
    }

    // public for Test visibility
    public void validateShelterLocation(ShelterDTO animalShelter) {
        String location = animalShelter.getLocation().toLowerCase(Locale.ROOT);

        if (!location.contains("brasov") && !location.contains("iasi")) {
            throw new ShelterLocationException("Brasov or Iasi is required");
        }
    }
    private void validateShelter(ShelterDTO shelterDTO){
        ApiError error = new ApiError(HttpStatus.CONFLICT, "Shelter validation failed");

        if(shelterDTO.getDogs().isEmpty()){
            error.getViolations().add(new Violation("dogs","Minimum 1 dog pls"));
        }
        if(shelterDTO.getName().contains("_")){
            error.getViolations().add(new Violation("name","No underscore ('_') in name"));

        }
        if(!error.getViolations().isEmpty()){
            throw  new ValidationException(error);
        }


    }

    @Validated(OnUpdate.class)
    public ShelterDTO updateShelter(Integer id, @Valid ShelterDTO animalShelter) {
        validateShelterLocation(animalShelter);
        validateShelter(animalShelter);

        AnimalShelter shelter = getShelterById(id);
        if (!shelter.getId().equals(animalShelter.getId())) {
            throw new RuntimeException("Id of entity not the same with path id");

        }
        return ShelterAdapter.toDTO(animalShelterRepository.save(ShelterAdapter.toEntity(animalShelter)));

    }

    public void deleteShelter(Integer id) {
        animalShelterRepository.deleteById(id);
    }

    public ShelterDTO findById(Integer id) {
        AnimalShelter shelter = getShelterById(id);
        return ShelterAdapter.toDTO(shelter);
    }

    public List<CatDTO> findAllCatsByShelter(Integer shelterId) {
//        Optional<AnimalShelter> optional = animalShelterRepository.findById(shelterId);
//        if(optional.isPresent()){
//            return optional.get().getCats();
//        }
//        throw new EntityNotFoundException("Shelter with id " + shelterId + " not found");

        AnimalShelter shelter = getShelterById(shelterId);
        return CatAdapter.toDTOList(shelter.getCats());
    }

    public List<CatDTO> addNewCatToShelter(Integer shelterId, CatDTO cat) {
//        Optional<AnimalShelter> optional = animalShelterRepository.findById(shelterId);


//
//        if(optional.isPresent()){
//            AnimalShelter shelter = optional.get();
//            shelter.getCats().add(cat);
//            animalShelterRepository.save(shelter);
//            return shelter.getCats();
//        }
        AnimalShelter shelter = getShelterById(shelterId);
        ShelterDTO shelterDTO = ShelterAdapter.toDTO(shelter);
        shelterDTO.getCats().add(cat);
        animalShelterRepository.save(ShelterAdapter.toEntity(shelterDTO));
        return shelterDTO.getCats();

//        return CatAdapter.toDTOList(animalShelterRepository.save(shelter.getCats().add(CatAdapter.toEntity(cat)))
//        throw new EntityNotFoundException("Shelter with id " + shelterId + " not found");

    }


    private AnimalShelter getShelterById(Integer id) {
        Optional<AnimalShelter> optional = animalShelterRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException(("Shelter with id " + id + " not found")));
    }
}
