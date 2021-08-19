package com.p5.adoptions.repository.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer>
{
    Optional<Animal> findByNameAndPhoto(String name, String photo);

    @Query("select a.id, a.name, a.photo from Animal a where a.name = :name")
    Optional<Animal> getByName(@Param("name") String name);

    @Query(value = "select * from animal where name = :name", nativeQuery = true)
    Optional<Animal> getNative(@Param("name") String name);

}
