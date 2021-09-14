package com.p5.adoptions.service.DTO;

public class CatDTO {
    private Integer id;
    private String name;
    private String photo;

    public CatDTO() {
    }

    public CatDTO(Integer id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public CatDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CatDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public CatDTO setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

}
