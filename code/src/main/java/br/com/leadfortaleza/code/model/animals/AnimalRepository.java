package br.com.leadfortaleza.code.model.animals;

import br.com.leadfortaleza.code.model.enums.AnimalClass;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.validation.Valid;

public interface AnimalRepository extends PagingAndSortingRepository<Animal, Long> {
    Iterable<Animal> getAnimalsByBreed(@Valid String breed);

    @Override
    @Query("select animal from Animal animal where animal.valid = true")
    Iterable<Animal> findAll();

    @Query("select animal from Animal animal where animal.valid = true and animal.animalClass = ?1")
    Iterable<Animal> findAnimalsByAnimalClass(@Param("animalClass") AnimalClass animalClass);

    @Query("select animal from Animal animal where animal.weight > :start and animal.weight < :end")
    Iterable<Animal> findAnimalsByWeightIsBetween(@Param("start") double start, @Param("end") double end);
}
