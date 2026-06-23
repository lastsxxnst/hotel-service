package com.example.hotel.cucumber.steps;

import com.example.hotel.dto.RoomRequest;
import com.example.hotel.model.Room;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

public class RoomSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ScenarioContext context;

    @When("user creates room")
    public void create_room() {

        RoomRequest request = new RoomRequest();
        request.setRoomNumber("101");
        request.setPrice(50.0);
        request.setType("DOUBLE");
        request.setHotelId(1L);

        ResponseEntity<Room> resp = restTemplate.postForEntity(
                "/rooms",
                request,
                Room.class
        );

        assertEquals(HttpStatus.OK, resp.getStatusCode());

        context.roomId = resp.getBody().getId();

        assertNotNull(context.roomId, "Room ID is NULL after creation");
    }

    @Then("room should be created")
    public void room_created() {
        assertNotNull(context.roomId);
    }

    @When("user deletes room")
    public void delete_room() {

        assertNotNull(context.roomId, "Room ID is null before DELETE");

        restTemplate.delete("/rooms/" + context.roomId);
    }

    @Then("room should be removed")
    public void room_removed() {

        ResponseEntity<String> resp = restTemplate.getForEntity(
                "/rooms/" + context.roomId,
                String.class
        );

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
    }
}