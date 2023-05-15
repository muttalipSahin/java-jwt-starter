package nl.inholland.apidemo.controllers;

import nl.inholland.apidemo.models.DTO.LoginRequestDTO;
import nl.inholland.apidemo.models.DTO.LoginResponseDTO;
import nl.inholland.apidemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequest) throws AuthenticationException {
        return userService.login(loginRequest);
    }
}
