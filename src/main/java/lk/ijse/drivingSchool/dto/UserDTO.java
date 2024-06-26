package lk.ijse.drivingSchool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class UserDTO {

    private String userName;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String address;
    private String password;
}
