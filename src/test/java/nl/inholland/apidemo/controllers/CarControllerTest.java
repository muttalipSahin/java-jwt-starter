package nl.inholland.apidemo.controllers;

import nl.inholland.apidemo.configuration.ApiTestConfiguration;
import nl.inholland.apidemo.models.Car;
import nl.inholland.apidemo.services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// IntelliJ probably already loads the spring context
// Just in case, we use @ExtendWith to ensure the context is loaded.
// We use @WebMvcTest because it allows us to only test the controller
// and not load in anything else (repositories, services etc.)
@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
@Import(ApiTestConfiguration.class)
public class CarControllerTest {

    // We use a unit test for controller methods to test any custom logic we have in there
    // In this case, we're using ModelMapper and we have to check if this produces the correct results

    // We use mockMvc to simulate HTTP requests to a controller class
    @Autowired
    private MockMvc mockMvc;

    // We mock our service, because we don't want to test it here
    // Note that we have to Mock all dependencies our controller code uses if we use @WebMvcTest
    @MockBean
    private CarService carService;

    // We could also add ObjectMapper to convert objects to JSON for us

    @BeforeEach
    void setUp() {


    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAll() throws Exception {
        // We 'Arrange', 'Act' and 'Assert' if the results match what we expect

        // First, we 'Arrange' everything for our test

        // Mockito allows us to 'inject' return values for methods we call
        // This way, we don't actually test the service, just the controller
        when(carService
                .getAll())
                .thenReturn(List.of(
                        new Car(1, "Honda", "AB1234", 1400, null)));

        // Check if we get a 200 OK
        // And if the JSON content matches our expectations
        this.mockMvc.perform(get("/cars")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].brand").value("Honda"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void add() throws Exception {

        // Arrange
        when(carService.add(any(Car.class))).thenReturn(new Car(2, "Mercedes", "CD4567", 2000, null));

        // Act & Assert
        this.mockMvc.perform(post("/cars").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        /// String literals in Java 17: enclose in """
                        .content("""
                                 {
                                    "brand": "Mercedes",
                                    "weight": 2000,
                                    "licensePlate": "CD4567"
                                  }
                                """))
                // But since we used any(Car.class) a simple {} should be enough
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brand").value("Mercedes"));
    }
}