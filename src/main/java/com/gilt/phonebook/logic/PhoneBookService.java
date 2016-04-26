package com.gilt.phonebook.logic;

import com.gilt.phonebook.controller.CreateContact;
import com.gilt.phonebook.repository.EntryEntity;
import com.gilt.phonebook.repository.EntryRepository;
import com.google.common.collect.Ordering;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import static com.google.common.collect.Iterables.transform;

@Service
@Transactional
public class PhoneBookService {

    private static final Ordering<Entry> ENTRY_ORDERING = Ordering.natural().onResultOf(Entry::getFirstName);

    @Inject
    private EntryRepository entryRepository;

    public Iterable<Entry> getContacts(SortDirection sortDirection) {

        Ordering<Entry> entryOrdering =
                sortDirection == SortDirection.ascending
                        ? ENTRY_ORDERING
                        : ENTRY_ORDERING.reverse();

        return entryOrdering.immutableSortedCopy(
                transform(
                        entryRepository.findAll(),
                        Entry::new
                )
        );
    }

    public void createContact(CreateContact contact) {
        entryRepository.create(new EntryEntity(
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhoneType(),
                contact.getPhoneNumber()
        ));
    }
}
