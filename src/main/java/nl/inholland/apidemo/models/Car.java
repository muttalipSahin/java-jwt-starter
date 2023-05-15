package nl.inholland.apidemo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue
    private long id;

    private String brand;

    @NotBlank(message = "License plate is mandatory")
    private String licensePlate;

    private int weight;

    public void setWeight(int value) {
        if(value < 0) {
            throw new IllegalArgumentException("Weight should not be less than 0");
        }
        weight = value;
    }

    @ManyToOne
    private Person owner;

}
