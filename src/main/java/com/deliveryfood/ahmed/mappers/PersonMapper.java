package com.deliveryfood.ahmed.mappers;

import com.deliveryfood.ahmed.dto.PersonDto;
import com.deliveryfood.ahmed.entity.Person;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonMapper {

    public final PersonAddressMapper personAddressMapper;

    public PersonMapper(PersonAddressMapper personAddressMapper) {
        this.personAddressMapper = personAddressMapper;
    }
    public PersonDto toPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        BeanUtils.copyProperties(person, personDto);
        personDto.setPersonAddressDto(personAddressMapper.toPersonAddressDtoList(person.getPersonAddresses()));
        return personDto;
    }

    public Person toPersonEntity(PersonDto personDto) {
        Person person = new Person();
        BeanUtils.copyProperties(personDto, person);
        person.setPersonAddresses(personAddressMapper.toPersonAddressEntityList(personDto.getPersonAddressDto()));
        return person;
    }

    public List<PersonDto> toPersonDtoList(List<Person> personList) {
        List<PersonDto> personDtoList = new ArrayList<>();
        for (Person person : personList) {
            PersonDto personDto = new PersonDto();
            BeanUtils.copyProperties(person, personDto);
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
            person.setPersonAddresses(personAddressMapper.toPersonAddressEntityList(personDto.getPersonAddressDto()));
            personEntityList.add(person);
        }
        return personEntityList;
    }
}
