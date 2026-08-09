package org.gd.eventhub.Controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.gd.eventhub.Services.EventService;
import org.gd.eventhub.dto.Requests.EventRequest;
import org.gd.eventhub.dto.Response.EventResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class EventController {
    private final EventService eventService;

    @PreAuthorize("hasAnyRole('ADMIN','ORGANIZER')")
    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@Valid @RequestBody EventRequest eventRequest) {

        EventResponse eventResponse = eventService.CreateEvent(eventRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable Integer id) {
        EventResponse  eventResponse = eventService.getEventById(id);
        return ResponseEntity.status(HttpStatus.OK).body(eventResponse);
    }

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents(@RequestParam(required = false,defaultValue = "1") Integer page, @RequestParam(required = false,defaultValue = "5") Integer size,
                                                            @RequestParam(required = false, defaultValue = "title")String sortBy, @RequestParam(required = false,defaultValue = "ASC")String sortOrder ) {
        Sort sort;
        if (sortOrder.equalsIgnoreCase("ASC")) {
            sort = Sort.by(Sort.Direction.ASC, sortBy);
        } else {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        }
        List<EventResponse> responses = eventService.getAllEvents(PageRequest.of(page-1,size,sort));
        return ResponseEntity.ok(responses);
    }


    @PreAuthorize("hasAnyRole('ADMIN','ORGANIZER')")
    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(@Valid @RequestBody EventRequest eventRequest,@PathVariable int id) {
        EventResponse response = eventService.UpdateEvent(eventRequest,id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @PreAuthorize("hasAnyRole('ADMIN','ORGANIZER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<EventResponse> deleteEvent(@PathVariable Integer id) {
        EventResponse response = eventService.DeleteEvent(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }




}
