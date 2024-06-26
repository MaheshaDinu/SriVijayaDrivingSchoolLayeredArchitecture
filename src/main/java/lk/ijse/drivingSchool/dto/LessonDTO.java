package lk.ijse.drivingSchool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class LessonDTO {
    private String date;
    private String time;
    private String instructorId;
    private String vehicleId;
}
