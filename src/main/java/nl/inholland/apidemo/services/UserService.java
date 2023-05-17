package nl.inholland.apidemo.services;

import nl.inholland.apidemo.jwt.JwtTokenProvider;
import nl.inholland.apidemo.models.DTO.LoginRequestDTO;
import nl.inholland.apidemo.models.DTO.LoginResponseDTO;
import nl.inholland.apidemo.models.User;
import nl.inholland.apidemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findUserByUsername(loginRequestDTO.getUsername())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Username or password is incorrect"));
        if (passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setToken(token);
            return loginResponseDTO;
        }
        throw new AuthenticationCredentialsNotFoundException("Username or password is incorrect");
    }
    public User addUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()).isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("Username is already taken");
    }
}
