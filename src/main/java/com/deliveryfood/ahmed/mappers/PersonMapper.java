package com.deliveryfood.ahmed.mappers;

import com.deliveryfood.ahmed.dto.PersonDto;
import com.deliveryfood.ahmed.entity.ERole;
import com.deliveryfood.ahmed.entity.Person;
import com.deliveryfood.ahmed.entity.Role;
import com.deliveryfood.ahmed.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonMapper {

    public final PersonAddressMapper personAddressMapper;

    public final RoleRepository roleRepository;

    public PersonMapper(PersonAddressMapper personAddressMapper, RoleRepository roleRepository) {
        this.personAddressMapper = personAddressMapper;
        this.roleRepository = roleRepository;
    }

    public PersonDto toPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        BeanUtils.copyProperties(person, personDto);
        Set<String> roles = new HashSet<>();
        person.getRoles().forEach(role -> {
            roles.add(role.getName().toString());
        });
        personDto.setRole(roles);
        personDto.setPersonAddressDto(personAddressMapper.toPersonAddressDtoList(person.getPersonAddresses()));
        return personDto;
    }

    public Person toPersonEntity(PersonDto personDto) {
        Person person = new Person();
        BeanUtils.copyProperties(personDto, person);
        Set<Role> roles = new HashSet<>();
        personDto.getRole().forEach(role -> {
            switch (role) {
                case "admin":
                    roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Role Not Found")));
                default:
                    roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Role Not Found")));
            }
        });
        person.setRoles(roles);
        person.setPersonAddresses(personAddressMapper.toPersonAddressEntityList(personDto.getPersonAddressDto()));
        return person;
    }

    public List<PersonDto> toPersonDtoList(List<Person> personList) {
        List<PersonDto> personDtoList = new ArrayList<>();
        for (Person person : personList) {
            PersonDto personDto = new PersonDto();
            BeanUtils.copyProperties(person, personDto);
            Set<String> roles = new HashSet<>();
            person.getRoles().forEach(role -> {
                roles.add(role.getName().toString());
            });
            personDto.setRole(roles);
            personDto.setPersonAddressDto(personAddressMapper.toPersonAddressDtoList(person.getPersonAddresses()));
            personDtoList.add(personDto);
        }
        return personDtoList;
    }

    public List<Person> toPersonEntityList(List<PersonDto> personDtoList) {
        List<Person> personEntityList = new ArrayList<>();
        for (PersonDto personDto : personDtoList) {
            Person person = new Person();
            BeanUtils.copyProperties(personDto, person);
            Set<Role> roles = new HashSet<>();
            personDto.getRole().forEach(role -> {
                switch (role) {
                    case "admin":
                        roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Role Not Found")));
                    default:
                        roles.add(roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Role Not Found")));
                }
            });
            person.setRoles(roles);
            person.setPersonAddresses(personAddressMapper.toPersonAddressEntityList(personDto.getPersonAddressDto()));
            personEntityList.add(person);
        }
        return personEntityList;
    }
}
