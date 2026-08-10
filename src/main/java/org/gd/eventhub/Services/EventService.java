package org.gd.eventhub.Services;


import lombok.RequiredArgsConstructor;
import org.gd.eventhub.Entity.Event;
import org.gd.eventhub.Entity.User;
import org.gd.eventhub.Entity.Venue;
import org.gd.eventhub.Enums.EventStatus;
import org.gd.eventhub.Exceptions.ForbiddenOperationException;
import org.gd.eventhub.Exceptions.InvalidCapacityException;
import org.gd.eventhub.Exceptions.ResourceNotFoundException;
import org.gd.eventhub.Exceptions.notFounEX.EventNotFoundException;
import org.gd.eventhub.Mapper.EventMapper;
import org.gd.eventhub.Repository.EventRepository;

import org.gd.eventhub.Repository.VenueRepository;
import org.gd.eventhub.dto.Requests.EventRequest;
import org.gd.eventhub.dto.Response.EventResponse;

import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final VenueRepository venueRepository;

    private final CurrentUserService currentUserService;






    public EventResponse CreateEvent(EventRequest request) {
        User organizer = currentUserService.getCurrentUser();


        Venue venue = venueRepository.findById(request.getVenueId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Venue not found"));
        if (request.getCapacity() > venue.getCapacity()) {
            throw new InvalidCapacityException(
                    "Event capacity cannot exceed venue capacity"
            );
        }


        Event event = eventMapper.toEntity(request);

        event.setOrganizer(organizer);
        event.setVenue(venue);
        event.setAvailableCapacity(event.getCapacity());
        event.setStatus(EventStatus.UPCOMING);

        Event savedEvent = eventRepository.save(event);
        return eventMapper.toResponse(savedEvent);

    }
    public EventResponse getEventById(Integer id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new EventNotFoundException("Event not found"));

        return eventMapper.toResponse(event);
    }

    public List<EventResponse> getAllEvents(Pageable  pageable) {

        return eventRepository.findAll(pageable)
                .stream()
                .map(eventMapper::toResponse)
                .toList();
    }

    public EventResponse UpdateEvent(EventRequest request,Integer id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new EventNotFoundException("Event not found"));

        User currentUser = currentUserService.getCurrentUser();

        if (!event.getOrganizer().getId().equals(currentUser.getId())) {
            throw new ForbiddenOperationException(
                    "You are not allowed to update this event"
            );
        }
        Venue venue = venueRepository.findById(request.getVenueId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Venue not found"));


        eventMapper.updateEntity(request, event);
        event.setVenue(venue);



        Event savedEvent = eventRepository.save(event);
        return eventMapper.toResponse(savedEvent);

    }

    public EventResponse DeleteEvent(Integer id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new EventNotFoundException("Event not found"));
        User currentUser = currentUserService.getCurrentUser();

        if (!event.getOrganizer().getId().equals(currentUser.getId())) {
            throw new ForbiddenOperationException(
                    "You are not allowed to delete this event"
            );
        }
        eventRepository.delete(event);
        return eventMapper.toResponse(event);

    }


}
