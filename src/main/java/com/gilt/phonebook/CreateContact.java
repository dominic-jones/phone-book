package com.gilt.phonebook;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class CreateContact {

    @NotNull
    @NotEmpty
    private String firstName;

    public CreateContact() {
    }

    public CreateContact(String firstName) {

        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }
}
