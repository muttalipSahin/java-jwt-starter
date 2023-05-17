package nl.inholland.apidemo.models.DTO;

import lombok.Data;
import nl.inholland.apidemo.models.Role;

import java.util.List;

@Data
public class LoginResponseDTO {
    private String token;

}
