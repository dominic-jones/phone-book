package com.gilt.phonebook;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.google.common.collect.Lists.newArrayList;

@RestController
public class PhoneBookController {

    public static final String HOME = "/";

    @RequestMapping(HOME)
    @ResponseBody
    Iterable<Entry> home() {
        return newArrayList(
                new Entry("testOne"),
                new Entry("testTwo")
        );
    }
}
