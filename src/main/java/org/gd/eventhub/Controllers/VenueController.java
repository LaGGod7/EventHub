package org.gd.eventhub.Controllers;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

import org.gd.eventhub.Services.VenueService;
import org.gd.eventhub.dto.Requests.VenueRequest;

import org.gd.eventhub.dto.Response.VenueResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venue")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class VenueController {

    private final VenueService venueService;

    @PreAuthorize("hasAnyRole('ADMIN','ORGANIZER')")
    @PostMapping
    public ResponseEntity<VenueResponse> createVenue(@Valid @RequestBody VenueRequest req) {
        VenueResponse resp = venueService.createVenue(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(resp);
    }


    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> getVenueById(@PathVariable int id) {
        VenueResponse resp = venueService.getVenueById(id);
        return ResponseEntity.ok(resp);
    }


    @GetMapping
    public ResponseEntity<List<VenueResponse>> getAllVenues(@RequestParam(required = false,defaultValue = "1") Integer page, @RequestParam(required = false,defaultValue = "5") Integer size,
                                                      @RequestParam(required = false, defaultValue = "name")String sortBy, @RequestParam(required = false,defaultValue = "ASC")String sortOrder ) {
        Sort sort;
        if (sortOrder.equalsIgnoreCase("ASC")) {
            sort = Sort.by(Sort.Direction.ASC, sortBy);
        } else {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        }
        List<VenueResponse> responses = venueService.getAllVenues(PageRequest.of(page-1,size,sort));
        return ResponseEntity.ok(responses);}

    @PreAuthorize("hasAnyRole('ADMIN','ORGANIZER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVenueById(@PathVariable int id) {
        VenueResponse response = venueService.deleteVenue(id);
        return ResponseEntity.ok(response.getId()+" has been deleted");
    }
    @PreAuthorize("hasAnyRole('ADMIN','ORGANIZER')")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable int id,@Valid @RequestBody VenueRequest req) {
        VenueResponse response = venueService.updateVenue(id,req);
        return ResponseEntity.ok(response.getId()+" has been updated");
    }




}
