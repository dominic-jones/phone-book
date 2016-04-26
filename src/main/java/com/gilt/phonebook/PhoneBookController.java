package com.gilt.phonebook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

import static com.gilt.phonebook.SortDirection.ascending;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(PhoneBookController.CONTACTS)
public class PhoneBookController {

    private static final Logger log = LoggerFactory.getLogger(PhoneBookController.class);

    public static final String CONTACTS = "/contacts";
    public static final String CREATE = "/create";

    @Inject
    private PhoneBookService phoneBookService;

    @RequestMapping
    @ResponseBody
    Iterable<Entry> readContacts() {
        return phoneBookService.getContacts(ascending);
    }

    @RequestMapping(value = CREATE, method = POST)
    void post(@RequestBody @Valid CreateContact contact) {
        log.info("Creating contact with firstName `{}`", contact.getFirstName());
    }
}
