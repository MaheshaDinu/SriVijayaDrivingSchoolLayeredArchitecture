package lk.ijse.drivingSchool.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentTm {
    private String id;
    private String date;
    private String studentName;
    private String amount;
}
