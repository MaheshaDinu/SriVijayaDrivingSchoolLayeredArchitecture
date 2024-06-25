package lk.ijse.drivingSchool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Vehicle {
    private String licensePlate;
    private String type;
    private String manufacturer;
    private String model;
    private String year;
    private String colour;
    private String transmissionType;
    private String fuelType;
    private String availability;

}
