package org.gd.eventhub.Mapper;

import org.gd.eventhub.Entity.Booking;
import org.gd.eventhub.dto.Requests.BookingRequest;
import org.gd.eventhub.dto.Response.BookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "bookingDate",ignore = true)
    @Mapping(target = "bookingReference",ignore = true)
    @Mapping(target = "totalPrice",ignore = true)
    @Mapping(target = "bookingStatus",ignore = true)
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "event",ignore = true)
    Booking toEntity(BookingRequest request);

    @Mapping(source = "event.title", target = "eventTitle")
    BookingResponse toBookingResponse(Booking booking);


}
