package com.gilt.phonebook;

import org.junit.Test;

import static com.google.common.collect.Iterables.transform;
import static org.assertj.core.api.Assertions.assertThat;

public class PhoneBookServiceTest {

    private PhoneBookService phoneBookService = new PhoneBookService();

    @Test
    public void givenValidWhenGettingContactsThenReturnAllInOrder() {
        Iterable<Entry> result = phoneBookService.getContacts();

        assertThat(
                transform(result, Entry::getName)
        ).containsExactly("testZeta", "testOne", "testTwo");
    }
}
