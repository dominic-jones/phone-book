package com.gilt.phonebook.logic;

import com.gilt.phonebook.controller.CreateContact;
import com.gilt.phonebook.repository.EntryEntity;
import com.gilt.phonebook.repository.EntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static com.google.common.base.MoreObjects.firstNonNull;
import static com.google.common.collect.FluentIterable.from;

@Service
@Transactional
public class PhoneBookService {

    @Inject
    private EntryRepository entryRepository;

    public Iterable<Entry> getContacts(SortField sortField,
                                       SortDirection sortDirection) {

        return from(entryRepository.findAll(
                        firstNonNull(sortField, SortField.firstName),
                        firstNonNull(sortDirection, SortDirection.ascending))
        )
                .transform(Entry::new)
                .toList();
    }

    public void createContact(CreateContact contact) {
        entryRepository.create(new EntryEntity(
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhoneType(),
                contact.getPhoneNumber()
        ));
    }

    public void delete(long id) {
        entryRepository.delete(id);
    }

    public void editContact(long id,
                            CreateContact contact) {
        entryRepository.edit(id, contact);
    }
}
