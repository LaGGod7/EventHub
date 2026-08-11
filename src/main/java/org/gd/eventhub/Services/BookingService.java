package org.gd.eventhub.Services;

import ch.qos.logback.core.status.Status;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.gd.eventhub.Entity.Booking;
import org.gd.eventhub.Entity.Event;
import org.gd.eventhub.Entity.User;
import org.gd.eventhub.Enums.BookingStatus;
import org.gd.eventhub.Enums.EventStatus;
import org.gd.eventhub.Exceptions.ForbiddenOperationException;
import org.gd.eventhub.Exceptions.InvalidStatusException;
import org.gd.eventhub.Exceptions.ResourceNotFoundException;
import org.gd.eventhub.Exceptions.SeatUnavailableException;
import org.gd.eventhub.Exceptions.notFounEX.EventNotFoundException;
import org.gd.eventhub.Mapper.BookingMapper;
import org.gd.eventhub.Repository.BookingRepository;
import org.gd.eventhub.Repository.EventRepository;
import org.gd.eventhub.Repository.VenueRepository;
import org.gd.eventhub.dto.Requests.BookingRequest;
import org.gd.eventhub.dto.Response.BookingResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final VenueRepository venueRepository;
    private final EventRepository eventRepository;
    private final BookingMapper bookingMapper;
    private final CurrentUserService currentUserService;

    @Transactional
    public BookingResponse createBooking(BookingRequest bookingRequest){
        User user = currentUserService.getCurrentUser();
        Event event = eventRepository.findById(bookingRequest.getEventId()).orElseThrow(()->new ResourceNotFoundException("Event not found"));
        if (event.getStatus() != EventStatus.AVAILABLE) {
            throw new EventNotFoundException(
                    "This event is not currently available for booking"
            );
        }
        if (event.getAvailableCapacity() < bookingRequest.getTicketCount()) {
            throw new SeatUnavailableException(
                    "Not enough seats available"
            );
        }

        double price = bookingRequest.getTicketCount()*event.getPrice();
        Booking booking = bookingMapper.toEntity(bookingRequest);
        booking.setEvent(event);
        booking.setUser(user);
        booking.setBookingReference(UUID.randomUUID().toString());
        booking.setBookingStatus(BookingStatus.PAYMENT_PENDING);
        booking.setTotalPrice(price);

        event.setAvailableCapacity(event.getAvailableCapacity()-bookingRequest.getTicketCount());
        eventRepository.save(event);
        bookingRepository.save(booking);

        return bookingMapper.toBookingResponse(booking);
    }

    public BookingResponse getBookingById(Integer id){
        Booking booking = bookingRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Booking not found"));
        BookingResponse response = bookingMapper.toBookingResponse(booking);
        return response;
    }

    public List<BookingResponse> getMyBooking(){
        User user = currentUserService.getCurrentUser();
        List<Booking> bookingList= bookingRepository.findByUser(user);
        return bookingList.stream().map(bookingMapper::toBookingResponse).toList();
    }
    @Transactional
    public BookingResponse cancelBooking(int id){
        User user = currentUserService.getCurrentUser();
        Booking booking = bookingRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Booking not found"));
        if (!booking.getUser().getId().equals(user.getId())) {
            throw new ForbiddenOperationException(
                    "You are not allowed to cancel this booking"
            );
        }
        if (booking.getBookingStatus() != BookingStatus.APPROVED) {
            throw new InvalidStatusException(
                    "Only approved bookings can be cancelled"
            );
        }
        Event event = booking.getEvent();
        event.setAvailableCapacity(event.getAvailableCapacity()+booking.getTicketCount());
        booking.setBookingStatus(BookingStatus.CANCELLED);


        eventRepository.save(event);
        bookingRepository.save(booking);

        return bookingMapper.toBookingResponse(booking);
    }







}
