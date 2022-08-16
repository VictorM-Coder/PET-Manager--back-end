package br.com.leadfortaleza.code.model.animals;

import br.com.leadfortaleza.code.model.enums.AnimalClass;
import br.com.leadfortaleza.code.model.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @JsonSerialize
    private Long id;

    @Column
    @NotBlank(message = "name cannot be blank")
    private String name;

    @Column
    @NotBlank(message = "breed cannot be blank")
    private String breed;

    @Column
    @Min(0)
    private double weight;
    private String imgLink;

    @Column
    @NotNull(message = "birthday date cannot be null")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;


    @Column
    private boolean vaccinated;

    @JsonIgnore
    @Column
    private boolean valid = true;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    @Enumerated(EnumType.STRING)
    private AnimalClass animalClass;

    public Animal(){

    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", weight=" + weight +
                ", imgLink='" + imgLink + '\'' +
                ", birthday=" + birthday +
                ", vaccinated=" + vaccinated +
                ", valid=" + valid +
                ", gender=" + gender +
                ", animalClass=" + animalClass +
                '}';
    }

    public void setInvalid(){
        this.valid = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public double getWeight() {
        return weight;
    }

    public String getImgLink() {
        return imgLink;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public boolean isValid() {
        return valid;
    }

    public Gender getGender() {
        return gender;
    }

    public AnimalClass getAnimalClass() {
        return animalClass;
    }
}
