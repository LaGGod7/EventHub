package org.gd.eventhub.dto.Response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VenueResponse {

    private Integer id;


    private String name;


    private String address;


    private String city;


    private String state;


    private String country;


    private Integer capacity;


}
