package com.gilt.phonebook.controller;

import com.gilt.phonebook.logic.Entry;
import com.gilt.phonebook.logic.PhoneBookService;
import com.gilt.phonebook.logic.SortDirection;
import com.gilt.phonebook.logic.SortField;
import com.gilt.phonebook.util.Loggable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping(PhoneBookController.CONTACTS)
public class PhoneBookController implements Loggable {

    public static final String CONTACTS = "/contacts";
    public static final String CREATE = "/create";

    @Inject
    private PhoneBookService phoneBookService;

    @ResponseBody
    @RequestMapping
    Iterable<Entry> readContacts(@PathParam("sortField") SortField sortField,
                                 @PathParam("sortDirection") SortDirection sortDirection) {
        return phoneBookService.getContacts(sortField, sortDirection);
    }

    @RequestMapping(value = CREATE, method = POST)
    void createContact(@RequestBody @Valid CreateContact contact) {

        log().info("Creating contact with firstName `{}`", contact.getFirstName());

        phoneBookService.createContact(contact);
    }

    @RequestMapping(value = "/{id}/edit", method = PUT)
    void editContact(@PathVariable("id") long id,
                     @RequestBody @Valid CreateContact contact) {

        log().info("Editing contact `{}` with firstName `{}`", id, contact.getFirstName());

        phoneBookService.editContact(id, contact);
    }

    @RequestMapping(value = "/{id}/delete", method = DELETE)
    void deleteContact(@PathVariable("id") long id) {

        log().info("Deleting contact with id `{}`", id);

        phoneBookService.delete(id);
    }
}
