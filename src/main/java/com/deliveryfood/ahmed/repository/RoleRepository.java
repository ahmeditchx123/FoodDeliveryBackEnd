package com.deliveryfood.ahmed.repository;

import com.deliveryfood.ahmed.entity.ERole;
import com.deliveryfood.ahmed.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
