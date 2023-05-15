package nl.inholland.apidemo.services;

import nl.inholland.apidemo.configuration.JwtTokenProvider;
import nl.inholland.apidemo.models.DTO.LoginRequestDTO;
import nl.inholland.apidemo.models.DTO.LoginResponseDTO;
import nl.inholland.apidemo.models.User;
import nl.inholland.apidemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public LoginResponseDTO login(LoginRequestDTO loginRequest) throws AuthenticationException {

        // Get the user from the database
        User user = userRepository.findUserByUsername(loginRequest.getUsername()).orElseThrow(() -> new AuthenticationException("User not found"));

        // Check if the password hash matches
        if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

            // Return a JWT to the client
            LoginResponseDTO response = new LoginResponseDTO();
            response.setToken(jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
            return response;

        } else {
            throw new AuthenticationException("Invalid username/password");
        }
    }

    public User addUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()).isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("Username is already taken");
    }
}
