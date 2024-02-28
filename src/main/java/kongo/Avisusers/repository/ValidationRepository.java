package kongo.Avisusers.repository;

import kongo.Avisusers.entites.Validation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation, Integer> {


    Optional<Validation> findByCode(String code);
}
