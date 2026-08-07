package org.gd.eventhub.dto.Requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;





@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VenueRequest {


    @NotBlank(message = "Venue name cannot be empty")
    private String name;

    @NotBlank(message = "address name cannot be empty")
    private String address;

    @NotBlank(message = "Specify the city")
    private String city;

    @NotBlank(message = "Specify the state")
    private String state;

    @NotBlank(message = "Specify the country")
    private String country;


    @Positive(message = "cannot be Negative")
    private Integer capacity;


}
