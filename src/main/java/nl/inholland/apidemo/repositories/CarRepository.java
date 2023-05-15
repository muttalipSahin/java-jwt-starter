package nl.inholland.apidemo.repositories;

import nl.inholland.apidemo.models.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Iterable<Car> findAllByBrandLike(String brand);
}
