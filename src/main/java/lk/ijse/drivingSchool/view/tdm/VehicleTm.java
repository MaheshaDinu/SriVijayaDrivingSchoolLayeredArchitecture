package lk.ijse.drivingSchool.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class VehicleTm {
    private String id;
    private String type;
    private String license;
    private String availability;
}
