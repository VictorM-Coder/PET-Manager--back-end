package br.com.leadfortaleza.code.controllers;

import br.com.leadfortaleza.code.model.animals.Animal;
import br.com.leadfortaleza.code.model.animals.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/animal")
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
