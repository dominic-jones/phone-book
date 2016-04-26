package com.gilt.phonebook;

import com.gilt.phonebook.controller.CreateContact;
import com.gilt.phonebook.logic.Entry;
import com.gilt.phonebook.logic.PhoneBookService;
import com.gilt.phonebook.logic.SortDirection;
import com.gilt.phonebook.logic.SortField;
import com.gilt.phonebook.repository.EntryEntity;
import com.gilt.phonebook.repository.EntryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.gilt.phonebook.logic.PhoneType.cell;
import static com.gilt.phonebook.logic.PhoneType.work;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PhoneBookServiceTest {

    private static final List<String> CONTACTS = newArrayList(
            "testOne",
            "testTwo",
            "testZeta"
    );
    @Captor
    ArgumentCaptor<EntryEntity> contactCaptor;
    @Mock
    private EntryRepository entryRepository;
    @InjectMocks
    private PhoneBookService phoneBookService;
    private SortField sortField = SortField.lastName;
    private SortDirection sortDirection = SortDirection.ascending;

    @Before
    public void setUp() {
        given(entryRepository.findAll(sortField, sortDirection))
                .willReturn(transform(CONTACTS, e -> new EntryEntity(e, e, cell, "0111111111")));
    }

    @Test
    public void givenValidWhenGettingContactsThenReturnAll() {
        Iterable<Entry> result = phoneBookService.getContacts(sortField, sortDirection);

        assertThat(
                transform(result, Entry::getFirstName)
        ).containsExactlyElementsOf(CONTACTS);
    }

    @Test
    public void givenValidWhenCreatingThenCreateValidEntity() {
        String firstName = "Rise";
        String lastName = "Kujikawa";
        phoneBookService.createContact(new CreateContact(firstName, lastName, work, "0111751247"));

        verify(entryRepository).create(contactCaptor.capture());
        assertThat(contactCaptor.getValue())
                .extracting(EntryEntity::getFirstName)
                .containsExactly(firstName);
    }
}
