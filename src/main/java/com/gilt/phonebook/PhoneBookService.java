package com.gilt.phonebook;

import com.google.common.collect.Ordering;
import org.springframework.stereotype.Service;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class PhoneBookService {

    private static final Ordering<Entry> ENTRY_ORDERING = Ordering.natural().onResultOf(Entry::getName);

    public Iterable<Entry> getContacts() {

        return ENTRY_ORDERING.immutableSortedCopy(data());
    }

    private Iterable<Entry> data() {
        return newArrayList(
                new Entry("testZeta"),
                new Entry("testOne"),
                new Entry("testTwo")
        );
    }
}
