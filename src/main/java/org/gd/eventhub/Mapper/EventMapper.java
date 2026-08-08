package org.gd.eventhub.Mapper;

import org.gd.eventhub.Entity.Event;
import org.gd.eventhub.dto.Requests.EventRequest;
import org.gd.eventhub.dto.Response.EventResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "availableCapacity", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "organizer", ignore = true)
    @Mapping(target = "venue", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    Event toEntity(EventRequest eventRequest);

    @Mapping(source = "venue.name", target = "venueName")
    @Mapping(source = "venue.city", target = "city")
    @Mapping(source = "organizer.name", target = "organizerName")
    EventResponse toResponse(Event event);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "availableCapacity", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "organizer", ignore = true)
    @Mapping(target = "venue", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    void updateEntity(EventRequest eventRequest, @MappingTarget Event event);
}
