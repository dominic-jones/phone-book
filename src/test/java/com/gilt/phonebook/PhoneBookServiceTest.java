package com.gilt.phonebook;

import org.junit.Test;

import java.util.List;

import static com.gilt.phonebook.SortDirection.ascending;
import static com.gilt.phonebook.SortDirection.descending;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;
import static org.assertj.core.api.Assertions.assertThat;

public class PhoneBookServiceTest {

    private static final List<String> CONTACTS = newArrayList("testOne", "testTwo", "testZeta");

    private PhoneBookService phoneBookService = new PhoneBookService();

    @Test
    public void givenValidWhenGettingContactsThenReturnAllInAscendingOrder() {
        Iterable<Entry> result = phoneBookService.getContacts(ascending);

        assertThat(
                transform(result, Entry::getName)
        ).containsExactlyElementsOf(CONTACTS);
    }

    @Test
    public void givenValidWhenGettingContactsThenReturnAllInDescendingOrder() {
        Iterable<Entry> result = phoneBookService.getContacts(descending);

        assertThat(
                transform(result, Entry::getName)
        ).containsExactlyElementsOf(reverse(CONTACTS));
    }
}
