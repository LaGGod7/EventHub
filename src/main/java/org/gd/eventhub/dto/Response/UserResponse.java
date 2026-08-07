package org.gd.eventhub.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gd.eventhub.Enums.Role;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;

    private String name;

    private String email;

    private String phoneNumber;

    private Role role;
}
