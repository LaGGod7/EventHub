package org.gd.eventhub.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.gd.eventhub.Enums.BookingStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {

    private String bookingReference;

    private String eventTitle;

    private Integer ticketCount;

    private LocalDateTime bookingDate;

    private BookingStatus bookingStatus;

    private Double  totalPrice;
}
