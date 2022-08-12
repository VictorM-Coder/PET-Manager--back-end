package br.com.leadfortaleza.code.model.animals;

import br.com.leadfortaleza.code.model.enums.AnimalClass;
import br.com.leadfortaleza.code.model.enums.Gender;

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
    private Long id;

    @Column
    @NotBlank(message = "name cannot be blank")
    private String name;

    @Column
    @NotBlank(message = "registration code cannot be blank")
    private String registerCode;

    @Column
    @NotBlank(message = "breed cannot be blank")
    private String breed;

    @Column
    @Min(0)
    private double weight;
    private String imgLink;

    @Column
    @NotNull(message = "birthday date cannot be null")
    private LocalDate birthday;


    @Column
    private boolean vaccinated;

    @Column
    private boolean valid;

    @Column
    @NotBlank(message = "Gender cannot be blank")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    @NotBlank(message = "Animal class cannot be blank")
    @Enumerated(EnumType.STRING)
    private AnimalClass animalClass;

    public Animal(){

    }

    public Animal(String name, String registerCode, String breed, double weight, String imgLink, LocalDate birthday, boolean vaccinated, boolean valid, Gender gender, AnimalClass animalClass) {
        this.name = name;
        this.registerCode = registerCode;
        this.breed = breed;
        this.weight = weight;
        this.imgLink = imgLink;
        this.birthday = birthday;
        this.vaccinated = vaccinated;
        this.valid = valid;
        this.gender = gender;
        this.animalClass = animalClass;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registerCode='" + registerCode + '\'' +
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRegisterCode() {
        return registerCode;
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
