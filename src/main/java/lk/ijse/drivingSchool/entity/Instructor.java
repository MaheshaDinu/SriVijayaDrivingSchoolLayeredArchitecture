package lk.ijse.drivingSchool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Instructor {
    private String firstName;
    private String lastname;
    private String licenseNumber;
    private String ContactNo;
}
