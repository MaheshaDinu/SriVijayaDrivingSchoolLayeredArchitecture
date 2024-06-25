package lk.ijse.drivingSchool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Payment {
    private String amount;
    private String date;
    private String studentId;
    private String vehicleClassId;
    private String userId;
}
