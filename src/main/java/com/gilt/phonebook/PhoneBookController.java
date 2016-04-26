package com.gilt.phonebook;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.google.common.collect.Lists.newArrayList;

@RestController
public class PhoneBookController {

    @RequestMapping("/")
    @ResponseBody
    Iterable<Entry> home() {
        return newArrayList(
                new Entry("testOne"),
                new Entry("testTwo")
        );
    }
}
