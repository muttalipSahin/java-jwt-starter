package nl.inholland.apidemo.configuration;

import nl.inholland.apidemo.models.Car;
import nl.inholland.apidemo.models.Person;
import nl.inholland.apidemo.services.CarService;
import nl.inholland.apidemo.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataSeeder implements ApplicationRunner {

    @Autowired
    CarService carService;

    @Autowired
    PersonService personService;

    @Override
    public void run(ApplicationArguments args) {

        Person person1 = new Person(1, "Luc", "Besson", new ArrayList<>());
        personService.add(person1);

        Car car1 = new Car(1, "Mercedes", "A class", 2000, person1);
        carService.add(car1);
    }
}
