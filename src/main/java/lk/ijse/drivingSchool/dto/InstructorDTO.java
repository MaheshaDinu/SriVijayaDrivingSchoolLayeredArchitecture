package lk.ijse.drivingSchool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class InstructorDTO {
    private String firstName;
    private String lastname;
    private String licenseNumber;
    private String ContactNo;
}
