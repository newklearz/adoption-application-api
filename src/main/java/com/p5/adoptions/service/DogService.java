package com.p5.adoptions.service;

import com.p5.adoptions.repository.dogs.Dog;
import com.p5.adoptions.repository.dogs.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    public void saveDog(Dog theDog){
        dogRepository.save(theDog);

    }



}
