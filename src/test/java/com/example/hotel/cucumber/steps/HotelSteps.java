package com.example.hotel.cucumber.steps;

import com.example.hotel.model.Hotel;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

public class HotelSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<Hotel[]> response;
    private Hotel createdHotel;
    private Long id;

    @When("user creates a hotel")
    public void create_hotel() {

        Hotel h = new Hotel();
        h.setName("Test Hotel");

        ResponseEntity<Hotel> resp = restTemplate.postForEntity(
                "/hotels",
                h,
                Hotel.class
        );

        assertNotNull(resp.getBody());

        createdHotel = resp.getBody();
        id = createdHotel.getId();

        assertNotNull(id);
    }

    @Then("hotel should be created successfully")
    public void hotel_created() {
        assertNotNull(id);
    }

    @When("user requests hotel by id")
    public void get_hotel_by_id() {
        ResponseEntity<Hotel> resp = restTemplate.getForEntity(
                "/hotels/" + id,
                Hotel.class
        );

        assertEquals(HttpStatus.OK, resp.getStatusCode());
    }

    @Then("correct hotel should be returned")
    public void correct_hotel() {
        assertTrue(id > 0);
    }

    @When("user deletes hotel")
    public void delete_hotel() {
        restTemplate.delete("/hotels/" + id);
    }

    @Then("hotel should be removed")
    public void hotel_removed() {

        ResponseEntity<Hotel> resp = restTemplate.getForEntity(
                "/hotels/" + id,
                Hotel.class
        );

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
    }

    @When("user requests all hotels")
    public void all_hotels() {

        ResponseEntity<Hotel[]> resp = restTemplate.getForEntity(
                "/hotels",
                Hotel[].class
        );

        response = resp;
    }

    @Then("response should not be empty")
    public void not_empty() {
        assertTrue(response.getBody().length > 0);
    }
}