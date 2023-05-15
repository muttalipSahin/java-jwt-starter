package nl.inholland.apidemo.services;

import nl.inholland.apidemo.exceptions.InhollandValidationException;
import nl.inholland.apidemo.models.Car;
import nl.inholland.apidemo.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll() {
        return (List<Car>) carRepository.findAll();
    }

    public Car getById(long id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car add(Car car) {
        return carRepository.save(car);
    }

    public Car update(Car car, long id) throws InhollandValidationException {
        Car existingCar = getById(id);

        existingCar.setBrand(car.getBrand());
        existingCar.setLicensePlate(car.getLicensePlate());

        try {
            return carRepository.save(existingCar);
        } catch(Exception ex) {
            throw new InhollandValidationException("Required fields missing");
        }
    }

    public void delete(long id) {
        carRepository.deleteById(id);
    }
}
