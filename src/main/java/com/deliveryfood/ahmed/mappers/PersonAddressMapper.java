package com.deliveryfood.ahmed.mappers;

import com.deliveryfood.ahmed.dto.PersonAddressDto;
import com.deliveryfood.ahmed.dto.PersonDto;
import com.deliveryfood.ahmed.entity.Person;
import com.deliveryfood.ahmed.entity.PersonAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonAddressMapper {

    public PersonAddressDto toPersonAddressDto(PersonAddress personAddress) {
        PersonAddressDto personAddressDto = new PersonAddressDto();
        BeanUtils.copyProperties(personAddress, personAddressDto);
        return personAddressDto;
    }

    public PersonAddress toPersonAddressEntity(PersonAddressDto personAddressDto) {
        PersonAddress personAddress = new PersonAddress();
        BeanUtils.copyProperties(personAddressDto, personAddress);
        return personAddress;
    }

    public Set<PersonAddressDto> toPersonAddressDtoList(Set<PersonAddress> personAddressList) {
        Set<PersonAddressDto> personAddressDtos = new HashSet<>();
        for (PersonAddress personAddress : personAddressList) {
            PersonAddressDto personAddressDto = new PersonAddressDto();
            BeanUtils.copyProperties(personAddress, personAddressDto);
            personAddressDtos.add(personAddressDto);
        }
        return personAddressDtos;
    }

    public Set<PersonAddress> toPersonAddressEntityList(Set<PersonAddressDto> personAddressDtos) {
        Set<PersonAddress> personAddressList = new HashSet<>();
        for (PersonAddressDto personAddressDto : personAddressDtos) {
            PersonAddress personAddress = new PersonAddress();
            BeanUtils.copyProperties(personAddressDto, personAddress);
            personAddressList.add(personAddress);
        }
        return personAddressList;
    }
}
