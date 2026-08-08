package org.gd.eventhub.Mapper;

import org.gd.eventhub.Entity.Venue;
import org.gd.eventhub.dto.Requests.VenueRequest;
import org.gd.eventhub.dto.Response.VenueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events",ignore = true)
    Venue toEntity(VenueRequest venueRequest);

    VenueResponse toResponse(Venue venue);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "events",ignore = true)
    void updateVenue(VenueRequest venueRequest,
                     @MappingTarget Venue venue);

}
