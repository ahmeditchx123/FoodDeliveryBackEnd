package com.deliveryfood.ahmed.service;

import com.deliveryfood.ahmed.dto.PersonDto;
import com.deliveryfood.ahmed.entity.Person;
import com.deliveryfood.ahmed.entity.Role;

import java.util.List;
import java.util.Set;

public interface PersonService {
    List<PersonDto> getAllPersons();
    PersonDto findById(Long id);
    PersonDto createPerson(PersonDto personDto, Set<Role> roles);
    PersonDto updatePerson(PersonDto personDto);
    void deletePerson(Long id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
