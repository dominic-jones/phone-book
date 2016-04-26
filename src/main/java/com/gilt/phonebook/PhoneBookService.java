package com.gilt.phonebook;

import org.springframework.stereotype.Service;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class PhoneBookService {

    public Iterable<Entry> getContacts() {

        return newArrayList(
                new Entry("testZeta"),
                new Entry("testOne"),
                new Entry("testTwo")
        );
    }
}
