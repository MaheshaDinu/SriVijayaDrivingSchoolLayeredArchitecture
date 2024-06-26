package lk.ijse.drivingSchool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentDTO {
    private String amount;
    private String date;
    private String studentId;
    private String vehicleClassId;
    private String userId;
}
