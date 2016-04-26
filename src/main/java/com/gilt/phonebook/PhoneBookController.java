package com.gilt.phonebook;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static com.gilt.phonebook.SortDirection.ascending;

@RestController
public class PhoneBookController {

    public static final String CONTACTS = "/contacts";

    @Inject
    private PhoneBookService phoneBookService;

    @RequestMapping(CONTACTS)
    @ResponseBody
    Iterable<Entry> readContacts() {
        return phoneBookService.getContacts(ascending);
    }

}
