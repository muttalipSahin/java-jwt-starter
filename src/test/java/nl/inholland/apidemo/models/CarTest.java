package nl.inholland.apidemo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void setBrand() {
    }

    @Test
    void carWeightShouldNotBeLessThanZero() {
        Car testCar = new Car();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            testCar.setWeight(-1);
        });

        assertEquals("Weight should not be less than 0", ex.getMessage());
    }

    @Test
    void carWeightShouldBeSet() {
        Car testCar = new Car();
        testCar.setWeight(100);
        assertEquals(100, testCar.getWeight());
    }
}