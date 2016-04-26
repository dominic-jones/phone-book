package com.gilt.phonebook.controller;

import com.gilt.phonebook.logic.Entry;
import com.gilt.phonebook.logic.PhoneBookService;
import com.gilt.phonebook.util.Loggable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

import static com.gilt.phonebook.logic.SortDirection.ascending;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(PhoneBookController.CONTACTS)
public class PhoneBookController implements Loggable {

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

        log().info("Creating contact with firstName `{}`", contact.getFirstName());

        phoneBookService.createContact(contact);
    }

    @RequestMapping(value = "/{id}/delete", method = DELETE)
    void delete(@PathVariable("id") long id) {

        log().info("Deleting contact with id `{}`", id);

        phoneBookService.delete(id);
    }
}
