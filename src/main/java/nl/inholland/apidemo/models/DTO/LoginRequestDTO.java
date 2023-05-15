package nl.inholland.apidemo.models.DTO;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}
