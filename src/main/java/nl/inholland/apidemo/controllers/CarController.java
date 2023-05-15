package nl.inholland.apidemo.controllers;

import nl.inholland.apidemo.exceptions.InhollandValidationException;
import nl.inholland.apidemo.models.Car;
import nl.inholland.apidemo.models.DTO.CarDTO;
import nl.inholland.apidemo.services.CarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("cars")
public class CarController {

    @Autowired
    private CarService carService;

    ModelMapper modelMapper;

    public CarController() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @GetMapping
    public List<CarDTO> getAll() {

        List<Car> cars = carService.getAll();

        // As an example, this code that was the first result from googling doesn't pass the test

        // List<CarDTO> carDTOS = new ArrayList<>();
        // modelMapper.map(cars,carDTOS);

        // This code passes the test

        List<CarDTO> dtos = Arrays.asList(modelMapper.map(cars, CarDTO[].class));

      //  List<CarDTO> dtos = cars.stream().map(car -> modelMapper.map(car, CarDTO.class)).collect(Collectors.toList());

        //return result
        return dtos;
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable long id) {
        return carService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Car> add(@RequestBody Car car) {

        return ResponseEntity.status(201).body(carService.add(car));
    }

    @PutMapping("/{id}")
    public Car update(@RequestBody Car car, @PathVariable long id) throws InhollandValidationException {

        return carService.update(car, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        carService.delete(id);
    }

}
