package br.com.leadfortaleza.code.controllers;

import br.com.leadfortaleza.code.model.animals.Animal;
import br.com.leadfortaleza.code.model.animals.AnimalRepository;
import br.com.leadfortaleza.code.model.enums.AnimalClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/animal")
@CrossOrigin(origins = "http://localhost:4200")
public class AnimalController {
    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping(path ="/animals")
    public @ResponseBody
    Iterable<Animal> get(){
        return this.animalRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Optional<Animal> get(@PathVariable Long id){
        return this.animalRepository.findById(id);
    }

    @GetMapping(path = "/animalClass/{animalClass}")
    public @ResponseBody
    Iterable<Animal> getByAnimalClass(@PathVariable String animalClass){
        try{
            AnimalClass value = (AnimalClass.valueOf(animalClass.toUpperCase()));
            return this.animalRepository.findAnimalsByAnimalClass(value);
        }catch (IllegalArgumentException exception){
            return null;
        }
    }

    @GetMapping(path = "/weightInterval")
    public @ResponseBody
    Iterable<Animal> getAnimalsByWeightInterval(@RequestParam(name = "start") double start, @RequestParam(name = "end") double end){
        return this.animalRepository.findAnimalsByWeightIsBetween(start, end);
    }

    @GetMapping(path = "/minWeight")
    public @ResponseBody
    Iterable<Animal> getAnimalByMinWeight(@RequestParam(name = "start") double start){
        return this.animalRepository.findAnimalsByWeightIsBetween(start, Double.MAX_VALUE);
    }

    @GetMapping(path = "maxWeight")
    public @ResponseBody
    Iterable<Animal> getAnimalByMaxWeight(@RequestParam(name = "end") double end){
        return this.animalRepository.findAnimalsByWeightIsBetween(Double.MIN_VALUE, end);
    }

    @GetMapping(path = "animalClass/weightInterval")
    public @ResponseBody
    Iterable<Animal> getAnimalByAnimalClassAndWeightInterval(@RequestParam(name = "animalClass") String animalClass, @RequestParam(name = "start") double start, @RequestParam(name = "end") double end){
        return this.animalRepository.findAnimalsByWeightIsBetweenAndAnimalClassAndValid(start, end, AnimalClass.valueOf(animalClass.toUpperCase()), true);
    }

    @GetMapping(path = "animalClass/maxWeight")
    public @ResponseBody
    Iterable<Animal> getAnimalByAnimalClassAndMaxWeight(@RequestParam(name = "animalClass") String animalClass, @RequestParam(name = "end") double end){
        return this.animalRepository.findAnimalsByWeightIsBetweenAndAnimalClassAndValid(Double.MIN_VALUE, end, AnimalClass.valueOf(animalClass.toUpperCase()), true);
    }

    @GetMapping(path = "animalClass/minWeight")
    public @ResponseBody
    Iterable<Animal> getAnimalByAnimalClassAndMinWeight(@RequestParam(name = "animalClass") String animalClass, @RequestParam(name = "start") double start){
        return this.animalRepository.findAnimalsByWeightIsBetweenAndAnimalClassAndValid(start, Double.MAX_VALUE, AnimalClass.valueOf(animalClass.toUpperCase()), true);
    }

    @PostMapping
    public @ResponseBody
    Animal post(@Valid @RequestBody Animal animal){
        return this.animalRepository.save(animal);
    }

    @PutMapping(path = "/delete/{id}")
    public @ResponseBody
    Optional<Animal> delete(@PathVariable Long id,@Valid @RequestBody Animal pet){

        Optional<Animal> animal = this.animalRepository.findById(id);
        if (animal.isPresent()){
            animal.get().setInvalid();
            this.animalRepository.save(animal.get());
        }
        return animal;
    }

    @PutMapping(path = "/update")
    public @ResponseBody
    Optional<Animal> update(@Valid @RequestBody Animal pet){
        Optional<Animal> animalActual = this.animalRepository.findById(pet.getId());
        if (animalActual.isPresent()){
            this.animalRepository.save(pet);
        }
        return animalActual;
    }
}
