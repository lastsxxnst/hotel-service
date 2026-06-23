package com.example.hotel.cucumber.steps;

import com.example.hotel.model.Guest;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

public class GuestSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ScenarioContext context;

    @When("user creates guest")
    public void create_guest() {

        Guest g = new Guest();
        g.setName("John");

        ResponseEntity<Guest> resp = restTemplate.postForEntity(
                "/guests",
                g,
                Guest.class
        );

        assertEquals(HttpStatus.OK, resp.getStatusCode());

        context.guestId = resp.getBody().getId();

        assertNotNull(context.guestId, "Guest ID is NULL after creation");
    }

    @Then("guest should be created")
    public void guest_created() {
        assertNotNull(context.guestId);
    }

    @When("user deletes guest")
    public void delete_guest() {

        assertNotNull(context.guestId, "Guest ID is null before DELETE");

        restTemplate.delete("/guests/" + context.guestId);
    }

    @Then("guest should be removed")
    public void guest_removed() {

        ResponseEntity<String> resp = restTemplate.getForEntity(
                "/guests/" + context.guestId,
                String.class
        );

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
    }
}