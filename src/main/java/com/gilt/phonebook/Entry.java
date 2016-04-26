package com.gilt.phonebook;

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
