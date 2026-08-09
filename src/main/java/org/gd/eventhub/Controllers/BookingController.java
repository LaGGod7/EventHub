package org.gd.eventhub.Controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gd.eventhub.Services.BookingService;
import org.gd.eventhub.dto.Requests.BookingRequest;
import org.gd.eventhub.dto.Response.BookingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class BookingController {
    private final BookingService bookingService;


    @PostMapping
    public ResponseEntity<BookingResponse> CreateBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        BookingResponse bookingResponse = bookingService.createBooking(bookingRequest);
        return ResponseEntity.ok(bookingResponse);
    }

    @GetMapping("/my")
    public ResponseEntity<List<BookingResponse>> GetMyBookings() {
        List<BookingResponse> responses = bookingService.getMyBooking();
        return ResponseEntity.ok(responses);
    }

    @PreAuthorize("hasAnyRole('ADMIN','ORGANIZER')")
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> GetBookingsById(@PathVariable int id) {
        BookingResponse response = bookingService.getBookingById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookingResponse> CancelBookingById(@PathVariable int id) {
        BookingResponse booking = bookingService.cancelBooking(id);
        return ResponseEntity.ok(booking);
    }

}
