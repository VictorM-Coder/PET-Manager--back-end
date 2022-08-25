package br.com.leadfortaleza.code.model.animals;

import br.com.leadfortaleza.code.model.enums.AnimalClass;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public interface AnimalRepository extends PagingAndSortingRepository<Animal, Long> {
    Iterable<Animal> getAnimalsByBreed(@Valid String breed);

    @Override
    @Query("select animal from Animal animal where animal.valid = true")
    Iterable<Animal> findAll();


    Iterable<Animal> findAnimalsByNameContainsAndAnimalClassAndValidIsTrue( String name, AnimalClass animalClass);

    Iterable<Animal> findAnimalsByNameContainsAndValidIsTrue(String name);

    Iterable<Animal> findAnimalsByNameContainsAndWeightBetweenAndValidIsTrue(String name, @Min(0) double weight, @Min(0) double weight2);

    Iterable<Animal> findAnimalsByNameContainsAndWeightBetweenAndAnimalClassAndValidIsTrue(String name, @Min(0) double weight, @Min(0) double weight2, AnimalClass animalClass);

}
