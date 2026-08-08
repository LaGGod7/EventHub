package org.gd.eventhub.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.gd.eventhub.Enums.Category;
import org.gd.eventhub.Enums.EventStatus;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {

    private Integer  id;


    private String title;

    private String description;


    private LocalDateTime eventDateTime;

    private Integer capacity;


    private Integer availableCapacity;


    private Category category;

    private EventStatus status;


    private Double  price;

    private String venueName;

    private String city;

    private String organizerName;

}
