package org.gd.eventhub.dto.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gd.eventhub.Enums.Category;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {


    @NotBlank(message = "Specify the Event Title")
    private String title;

    @NotBlank(message = "Give an event Description")
    private String description;

    @NotNull(message = "Event date and time is required")
    private LocalDateTime eventDateTime;

    @Positive(message = "Specify the Total capacity")
    private Integer capacity;



    @NotNull(message = "Specify the category")
    private Category category;



    @Positive(message = "Specify the Price")
    private Double  price;


    @NotNull
    @Positive(message = "Specify a valid id")
    private Integer venueId;



}
