package lk.ijse.drivingSchool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Lesson {
    private String date;
    private String time;
    private String instructorId;
    private String vehicleId;
}
