package com.deliveryfood.ahmed.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "username")
    private String username;

    @NotNull
    @Size(max = 100)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(max = 100)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Size(max = 100)
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Size(max = 100)
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(max = 100)
    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<PersonAddress> personAddresses = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PersonAddress> getPersonAddresses() {
        return personAddresses;
    }

    public void setPersonAddresses(Set<PersonAddress> personAddresses) {
        this.personAddresses = personAddresses;
    }
}
