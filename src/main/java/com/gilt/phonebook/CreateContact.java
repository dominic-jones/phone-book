package com.gilt.phonebook;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class CreateContact {

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    private PhoneType phoneType;

    @NotNull
    @NotEmpty
    private String phoneNumber;

    public CreateContact() {
    }

    public CreateContact(String firstName,
                         String lastName,
                         PhoneType phoneType,
                         String phoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneType = phoneType;
        this.phoneNumber = phoneNumber;
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
