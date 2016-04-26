package com.gilt.phonebook;

import com.gilt.phonebook.controller.CreateContact;
import com.gilt.phonebook.controller.PhoneBookController;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
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
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.hasItems;

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
                .body("firstName", hasItems("Chie", "Yosuke", "Yu", "Yukiko"));
    }

    @Test
    public void createContact() {
        given()
                .contentType(ContentType.JSON)
                .body(new CreateContact("Rise", "Kujikawa", work, "0111751247"))
                .when()
                .post(PhoneBookController.CONTACTS + PhoneBookController.CREATE)
                .then()
                .statusCode(SC_OK);
    }
}
