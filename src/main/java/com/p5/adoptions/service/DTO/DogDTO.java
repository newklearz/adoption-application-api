package com.p5.adoptions.service.DTO;

public class DogDTO  {
    private Integer id;
    private String name;
    private String photo;

    public DogDTO() {
    }

    public DogDTO(Integer id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public DogDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DogDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public DogDTO setPhoto(String photo) {
        this.photo = photo;
        return this;
    }
}
