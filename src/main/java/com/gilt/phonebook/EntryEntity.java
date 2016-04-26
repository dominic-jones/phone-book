package com.gilt.phonebook;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String firstName;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
}
