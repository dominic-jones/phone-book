package com.gilt.phonebook.repository;

import com.gilt.phonebook.logic.PhoneType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "entries")
public class EntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String firstName;
    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String lastName;
    @Enumerated(EnumType.STRING)
    @Column
    private PhoneType phoneType;
    @NotNull
    @NotEmpty
    @Column
    private String phoneNumber;

    public EntryEntity() {
    }

    public EntryEntity(String firstName,
                       String lastName,
                       PhoneType phoneType,
                       String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
