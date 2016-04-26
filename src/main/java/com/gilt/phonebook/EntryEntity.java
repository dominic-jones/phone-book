package com.gilt.phonebook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "entries")
public class EntryEntity {

    public EntryEntity() {
    }

    public EntryEntity(String firstName) {
        this.firstName = firstName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
}
