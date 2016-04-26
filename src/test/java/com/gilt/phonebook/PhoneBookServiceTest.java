package com.gilt.phonebook;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneBookServiceTest {

    private PhoneBookService phoneBookService = new PhoneBookService();

    @Test
    public void givenDummyServiceThenPass() {
        Iterable<Entry> result = phoneBookService.getContacts();

        assertThat(result).isEmpty();
    }
}