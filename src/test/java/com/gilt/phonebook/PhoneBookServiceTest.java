package com.gilt.phonebook;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.gilt.phonebook.PhoneType.cell;
import static com.gilt.phonebook.PhoneType.work;
import static com.gilt.phonebook.SortDirection.ascending;
import static com.gilt.phonebook.SortDirection.descending;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;
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

    @Before
    public void setUp() {
        given(entryRepository.findAll())
                .willReturn(transform(CONTACTS, e -> new EntryEntity(e, e, cell, "0111111111")));
    }

    @Test
    public void givenValidWhenGettingContactsThenReturnAllInAscendingOrder() {
        Iterable<Entry> result = phoneBookService.getContacts(ascending);

        assertThat(
                transform(result, Entry::getFirstName)
        ).containsExactlyElementsOf(CONTACTS);
    }

    @Test
    public void givenValidWhenGettingContactsThenReturnAllInDescendingOrder() {
        Iterable<Entry> result = phoneBookService.getContacts(descending);

        assertThat(
                transform(result, Entry::getFirstName)
        ).containsExactlyElementsOf(reverse(CONTACTS));
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
