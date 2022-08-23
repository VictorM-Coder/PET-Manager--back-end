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

    @PostMapping
    public @ResponseBody
    Animal post(@Valid @RequestBody Animal animal){
        return this.animalRepository.save(animal);
    }

    @PutMapping(path = "/delete/{id}")
    public @ResponseBody
    Optional<Animal> delete(@PathVariable Long id){
        Optional<Animal> animal = this.animalRepository.findById(id);
        if (animal.isPresent()){
            animal.get().setInvalid();
            this.animalRepository.save(animal.get());
        }

        return animal;
    }
}
