package com.gilt.phonebook;

import com.gilt.phonebook.controller.CreateContact;
import com.gilt.phonebook.controller.PhoneBookController;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.gilt.phonebook.logic.PhoneType.work;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PhoneBookApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class PhoneBookControllerIT {

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void readContacts() {
        when()
                .get(PhoneBookController.CONTACTS)
                .then()
                .statusCode(SC_OK)
                .body("firstName", contains("Chie", "Yosuke", "Yu", "Yukiko"));
    }

    @Test
    public void createContact() {
        String expectedNumber = "0111751247";

        given()
                .contentType(JSON)
                .body(new CreateContact("Rise", "Kujikawa", work, expectedNumber))
                .when()
                .post(PhoneBookController.CONTACTS + PhoneBookController.CREATE)
                .then()
                .statusCode(SC_OK);

        when()
                .get(PhoneBookController.CONTACTS)
                .then()
                .body("find { it.firstName == 'Rise' }.phoneNumber", equalTo(expectedNumber));
    }

    @Test
    public void editContact() {
        String expectedNumber = "0777777777";

        given()
                .contentType(JSON)
                .body(new CreateContact("Chie", "Satonaka", work, expectedNumber))
                .when()
                .put(PhoneBookController.CONTACTS + "/{id}/edit", 2)
                .then()
                .statusCode(SC_OK);

        when()
                .get(PhoneBookController.CONTACTS)
                .then()
                .body("find { it.firstName == 'Chie' }.phoneNumber", equalTo(expectedNumber));
    }

    @Test
    public void deleteContact() {
        given()
                .delete(PhoneBookController.CONTACTS + "/{id}/delete", 2)
                .then()
                .statusCode(SC_OK);

        when()
                .get(PhoneBookController.CONTACTS)
                .then()
                .body("find { it.id == 2 }", equalTo(null));
    }
}
