package com.gilt.phonebook;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static com.gilt.phonebook.SortDirection.ascending;

@RestController
public class PhoneBookController {

    public static final String HOME = "/";

    @Inject
    private PhoneBookService phoneBookService;

    @RequestMapping(HOME)
    @ResponseBody
    Iterable<Entry> home() {
        return phoneBookService.getContacts(ascending);
    }

}
