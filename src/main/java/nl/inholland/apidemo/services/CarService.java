package nl.inholland.apidemo.services;

import nl.inholland.apidemo.exceptions.InhollandValidationException;
import nl.inholland.apidemo.models.Car;

import java.util.List;

public interface CarService {
    List<Car> getAll();

    Car getById(long id);

    Car add(Car car);

    Car update(Car car, long id) throws InhollandValidationException;

    void delete(long id);
}
