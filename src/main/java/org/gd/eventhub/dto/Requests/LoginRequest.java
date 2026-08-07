package org.gd.eventhub.dto.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "email Cant be blank")
    @Email(message = "Enter a valid email")
    private String email;
    @NotBlank(message = "password Cant be blank")
    private String password;
}
