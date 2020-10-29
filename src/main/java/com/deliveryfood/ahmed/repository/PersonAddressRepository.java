package com.deliveryfood.ahmed.repository;

import com.deliveryfood.ahmed.entity.PersonAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonAddressRepository extends JpaRepository<PersonAddress, Long> {
}
