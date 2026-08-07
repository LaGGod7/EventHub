package org.gd.eventhub.dto.Requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @NotNull(message = "plz specify event id")
    @Positive
    private Integer eventId;

    @NotNull
    @Positive(message = "Specify a valid count")
    private Integer ticketCount;
}
