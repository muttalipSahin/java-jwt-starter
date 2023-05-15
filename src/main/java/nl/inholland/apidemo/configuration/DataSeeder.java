package nl.inholland.apidemo.configuration;

import nl.inholland.apidemo.models.Car;
import nl.inholland.apidemo.models.Person;
import nl.inholland.apidemo.models.Role;
import nl.inholland.apidemo.models.User;
import nl.inholland.apidemo.services.CarService;
import nl.inholland.apidemo.services.PersonService;
import nl.inholland.apidemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder implements ApplicationRunner {

    @Autowired
    CarService carService;

    @Autowired
    PersonService personService;

    @Autowired
    UserService userService;

    @Override
    public void run(ApplicationArguments args) {

        Person person1 = new Person(1, "Luc", "Besson", new ArrayList<>());
        personService.add(person1);

        Car car1 = new Car(1, "Mercedes", "A class", 2000, person1);
        carService.add(car1);

        User user1 = new User(1, "admin", "admin", List.of(Role.ROLE_ADMIN));
        userService.addUser(user1);
    }
}
