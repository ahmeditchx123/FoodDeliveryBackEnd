package com.deliveryfood.ahmed.service;

import com.deliveryfood.ahmed.dto.PersonDto;
import com.deliveryfood.ahmed.entity.Person;

import java.util.List;

public interface PersonService {
    List<PersonDto> getAllPersons();
    PersonDto findById(Long id);
    PersonDto createPerson(PersonDto personDto);
    PersonDto updatePerson(PersonDto personDto);
    void deletePerson(Long id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
