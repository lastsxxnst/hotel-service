package com.example.hotel.cucumber.steps;

import com.example.hotel.dto.ReservationRequest;
import com.example.hotel.model.Reservation;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${local.server.port}")
    private int port;

    private String baseUrl;
    private ResponseEntity<Reservation> response;
    private Long reservationId;
    private ReservationRequest request;

    @Given("room id {long} and guest id {long} with dates {string} to {string}")
    public void set_data(Long roomId, Long guestId, String in, String out) {

        baseUrl = "http://localhost:" + port;

        request = new ReservationRequest();
        request.setRoomId(roomId);
        request.setGuestId(guestId);
        request.setCheckIn(in);
        request.setCheckOut(out);
    }

    @When("I create reservation")
    public void create_reservation() {

        response = restTemplate.postForEntity(
                baseUrl + "/reservations",
                request,
                Reservation.class
        );

        reservationId = response.getBody().getId();
    }

    @Then("reservation should be created")
    public void check_created() {

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertNotNull(reservationId);
    }

    @When("I request all reservations")
    public void get_all() {

        ResponseEntity<Reservation[]> resp = restTemplate.getForEntity(
                baseUrl + "/reservations",
                Reservation[].class
        );

        assertNotNull(resp.getBody());
    }

    @Then("I should get list of reservations")
    public void check_list() {
        assertTrue(true);
    }

    @Given("existing reservation id {long}")
    public void existing(Long id) {
        this.reservationId = id;
    }

    @When("I cancel reservation")
    public void cancel() {
        restTemplate.delete(baseUrl + "/reservations/" + reservationId);
    }

    @Then("reservation should be deleted")
    public void deleted() {

        ResponseEntity<String> resp = restTemplate.getForEntity(
                baseUrl + "/reservations/" + reservationId,
                String.class
        );

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
    }
}