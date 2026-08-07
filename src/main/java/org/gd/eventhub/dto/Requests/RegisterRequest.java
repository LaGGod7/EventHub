package org.gd.eventhub.dto.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "name cannot be null")
    private String name;
    @NotBlank(message = "email Cant be blank")
    private String email;
    @NotBlank(message = "password Cant be blank")
    private String password;
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Phone number must be a valid 10-digit Indian mobile number"
    )
    private String phoneNumber;

}
