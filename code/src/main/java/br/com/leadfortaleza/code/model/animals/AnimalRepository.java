package br.com.leadfortaleza.code.model.animals;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AnimalRepository extends PagingAndSortingRepository<Animal, Long> {
}
