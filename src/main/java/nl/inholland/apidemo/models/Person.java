package nl.inholland.apidemo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    public void setLastName(String value) {
        if(value.length() < 5) {
            throw new IllegalArgumentException("Value too short (less than 5 characters)");
        }
        lastName = value;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Car> cars;
}
