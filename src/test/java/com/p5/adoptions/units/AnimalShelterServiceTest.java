package com.p5.adoptions.units;


import com.p5.adoptions.repository.shelters.AnimalShelter;
import com.p5.adoptions.repository.shelters.AnimalShelterRepository;
import com.p5.adoptions.service.AnimalShelterService;
import com.p5.adoptions.service.DTO.ShelterDTO;
import com.p5.adoptions.service.adapters.ShelterAdapter;
import com.p5.adoptions.service.exceptions.ShelterLocationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
// ExtendWith is used for the second way of injecting mocks

@ExtendWith(MockitoExtension.class)
public class AnimalShelterServiceTest
{
    private ShelterDTO shelterDTO;
    private AnimalShelter shelterJpa;
    @BeforeEach
    public void setUp(){

        shelterDTO= new ShelterDTO()
                .setId(123)
                .setName("Pentalog's Shelter")
                .setLocation("Iasi")
                .setCats(Collections.emptyList())
                .setDogs(Collections.emptyList());

        this.shelterJpa = ShelterAdapter.toEntity(shelterDTO);
    }
    @Test
    public void testValidateShelter_RunsOK(){
        AnimalShelterService animalShelterService = new AnimalShelterService(null);

        animalShelterService.validateShelterLocation(shelterDTO);
    }

    @Test
    public void testValidateShelter_Fails(){
        AnimalShelterService animalShelterService = new AnimalShelterService(null);
        shelterDTO.setLocation("Cluj");

       ShelterLocationException exception = Assertions.assertThrows(ShelterLocationException.class, ()->animalShelterService.validateShelterLocation(shelterDTO));
       String expectedError =  "Brasov or Iasi s required";
       Assertions.assertNotNull(exception, "Exception was null");
       Assertions.assertEquals(expectedError, exception.getMessage(), "Exception message did not match");
    }

    @Test
    public void createShelter_SavesOk(){
        AnimalShelterRepository shelterRepositoryMock = Mockito.mock(AnimalShelterRepository.class);



        AnimalShelterService animalShelterService = new AnimalShelterService(shelterRepositoryMock);

        Mockito.when(shelterRepositoryMock.save(Mockito.any())).thenReturn(shelterJpa);

        ShelterDTO savedShelter = animalShelterService.createShelter(shelterDTO);


        Assertions.assertNotNull(savedShelter);
        Assertions.assertEquals(savedShelter, shelterDTO);


    }
    // Method 2 of injecting mocks

    @Mock
    private AnimalShelterRepository animalShelterRepositoryMock;

    @InjectMocks
    private AnimalShelterService animalShelterService;

    @Test
    public void deleteShelter_VerifyParam(){
        animalShelterService.deleteShelter(shelterDTO.getId());

        // Verify how many times a method is called
        Mockito.verify(animalShelterRepositoryMock,Mockito.times(1) ).deleteById(Mockito.anyInt());

        // Verify the param that it was called with

        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);
        Mockito.verify(animalShelterRepositoryMock).deleteById(argumentCaptor.capture());

        Integer capturedValue = argumentCaptor.getValue();

        Assertions.assertNotNull(capturedValue);
        Assertions.assertEquals(capturedValue, shelterDTO.getId());
    }
}
