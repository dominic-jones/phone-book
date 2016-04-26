package com.gilt.phonebook.logic;

import com.gilt.phonebook.repository.EntryEntity;

public class Entry {

    private Long id;
    private String firstName;
    private String lastName;
    private PhoneType phoneType;
    private String phoneNumber;

    public Entry(EntryEntity entryEntity) {
        this.firstName = entryEntity.getFirstName();
        this.lastName = entryEntity.getLastName();
        this.phoneType = entryEntity.getPhoneType();
        this.phoneNumber = entryEntity.getPhoneNumber();
        this.id = entryEntity.getId();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
