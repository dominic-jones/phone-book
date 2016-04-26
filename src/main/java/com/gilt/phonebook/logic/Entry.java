package com.gilt.phonebook.logic;

import com.gilt.phonebook.repository.EntryEntity;

public class Entry {

    private String firstName;

    public Entry(String firstName) {
        this.firstName = firstName;
    }

    public Entry(EntryEntity entryEntity) {
        this.firstName = entryEntity.getFirstName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
