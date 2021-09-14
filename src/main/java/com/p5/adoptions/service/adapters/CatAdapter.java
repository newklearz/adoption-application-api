package com.p5.adoptions.service.adapters;

import com.p5.adoptions.repository.cats.Cat;
import com.p5.adoptions.service.DTO.CatDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CatAdapter {
    public static CatDTO toDTO (Cat cat){
        return new CatDTO()
                .setId(cat.getId())
                .setName(cat.getName())
                .setPhoto(cat.getPhoto());
    }

    public static Cat toEntity (CatDTO catDTO){
        Cat cat = new Cat();
        cat.setId(catDTO.getId());
        cat.setName(catDTO.getName());
        cat.setPhoto(catDTO.getPhoto());
        return cat;
    }

    public static List<CatDTO> toDTOList (List<Cat> catList){
        return catList
                .stream()
                .map(CatAdapter::toDTO)
                .collect(Collectors.toList());
    }
    public static  List<Cat> toEntityList(List<CatDTO> catDTOList){
        return catDTOList
                .stream()
                .map(CatAdapter::toEntity)
                .collect(Collectors.toList());
    }
}
