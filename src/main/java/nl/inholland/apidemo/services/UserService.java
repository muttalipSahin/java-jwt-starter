package nl.inholland.apidemo.services;

import nl.inholland.apidemo.models.User;
import nl.inholland.apidemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()).isEmpty()) {
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("Username is already taken");
    }
}
