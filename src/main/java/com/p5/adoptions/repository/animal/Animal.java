package com.p5.adoptions.repository.animal;

import org.hibernate.SessionFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

//Option 1
//@MappedSuperclass

//Option 2
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
////Only if we use SINGLE_TABLE
//@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Animal
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String photo;

    //    Field used in discriminator logic CAT or DOG
    //    private String type;


    public Animal(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public Animal() {

    }

    public String getName()
    {
        return name;
    }

    public Animal setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getPhoto()
    {
        return photo;
    }

    public Animal setPhoto(String photo)
    {
        this.photo = photo;
        return this;
    }

    public Integer getId()
    {
        return id;
    }

    public Animal setId(Integer id)
    {
        this.id = id;
        return this;
    }

}
