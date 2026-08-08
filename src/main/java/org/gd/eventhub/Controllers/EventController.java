package org.gd.eventhub.Controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gd.eventhub.Services.EventService;
import org.gd.eventhub.dto.Requests.EventRequest;
import org.gd.eventhub.dto.Response.EventResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponse> CreateEvent(@Valid @RequestBody EventRequest eventRequest) {

        EventResponse eventResponse = eventService.CreateEvent(eventRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventResponse);
    }
}
