package com.gilt.phonebook;

import com.google.common.collect.Ordering;
import org.springframework.stereotype.Service;

import static com.gilt.phonebook.SortDirection.ascending;
import static com.google.common.collect.Lists.newArrayList;

@Service
public class PhoneBookService {

    private static final Ordering<Entry> ENTRY_ORDERING = Ordering.natural().onResultOf(Entry::getName);

    public Iterable<Entry> getContacts(SortDirection sortDirection) {

        Ordering<Entry> entryOrdering =
                sortDirection == ascending
                        ? ENTRY_ORDERING
                        : ENTRY_ORDERING.reverse();

        return entryOrdering.immutableSortedCopy(data());
    }

    private Iterable<Entry> data() {
        return newArrayList(
                new Entry("testZeta"),
                new Entry("testOne"),
                new Entry("testTwo")
        );
    }
}
