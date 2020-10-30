package com.deliveryfood.ahmed.service.impl;

import com.deliveryfood.ahmed.dto.PersonDto;
import com.deliveryfood.ahmed.entity.Person;
import com.deliveryfood.ahmed.entity.Role;
import com.deliveryfood.ahmed.mappers.PersonMapper;
import com.deliveryfood.ahmed.repository.PersonRepository;
import com.deliveryfood.ahmed.service.PersonService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    public final PersonRepository personRepository;

    public final PersonMapper personMapper;

    private final PasswordEncoder passwordEncoder;

    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<PersonDto> getAllPersons() {
        return personMapper.toPersonDtoList(personRepository.findAll());
    }

    @Override
    public PersonDto findById(Long id) {
        return personMapper.toPersonDto(personRepository.getOne(id));
    }

    @Override
    public PersonDto createPerson(PersonDto personDto, Set<Role> roles) {
        Person person = personMapper.toPersonEntity(personDto);
        person.setPassword(passwordEncoder.encode(personDto.getPassword()));
        person.setRoles(roles);
        return personMapper.toPersonDto(personRepository.save(person));
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto) {
        return personMapper.toPersonDto(personRepository.save(personMapper.toPersonEntity(personDto)));
    }

    @Override
    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return personRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }
}
