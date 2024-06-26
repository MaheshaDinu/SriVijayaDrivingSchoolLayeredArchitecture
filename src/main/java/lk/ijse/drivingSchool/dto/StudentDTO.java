package lk.ijse.drivingSchool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class StudentDTO {

    private String NIC;
    private String firstName;
    private String lastName;

    private String height;
    private String weight;
    private String dateOfBirth;
    private String bloodGroup;
    private String contactNo;
    private String address;
    private String userId;
    private String vehicleClassId;

    public  String getFullName(){
        return firstName+" "+lastName;
    }



}
