package com.gilt.phonebook;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneBookController {

    @RequestMapping(name = "/", produces = "application/json")
    @ResponseBody
    String home() {
        return "{\"name\":\"test\"}";
    }
}
