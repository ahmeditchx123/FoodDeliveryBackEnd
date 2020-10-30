package com.deliveryfood.ahmed.repository;

import com.deliveryfood.ahmed.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<Person> findByUsername(String username);
}
