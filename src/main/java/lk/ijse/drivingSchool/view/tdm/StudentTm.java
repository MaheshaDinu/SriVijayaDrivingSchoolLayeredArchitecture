package lk.ijse.drivingSchool.view.tdm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class StudentTm {
    private String id;
    private String NIC;
    private String name;
    private String vehicleClass;
    private JFXButton btnProfile;
}
