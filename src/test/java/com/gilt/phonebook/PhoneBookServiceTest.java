package com.gilt.phonebook;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.gilt.phonebook.SortDirection.ascending;
import static com.gilt.phonebook.SortDirection.descending;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PhoneBookServiceTest {

    private static final List<String> CONTACTS = newArrayList(
            "testOne",
            "testTwo",
            "testZeta"
    );

    @Mock
    private EntryRepository entryRepository;

    @InjectMocks
    private PhoneBookService phoneBookService;

    @Before
    public void setUp() {
        given(entryRepository.findAll())
                .willReturn(transform(CONTACTS, this::create));
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

    EntryEntity create(String firstName) {
        return new EntryEntity(firstName);
    }
}
