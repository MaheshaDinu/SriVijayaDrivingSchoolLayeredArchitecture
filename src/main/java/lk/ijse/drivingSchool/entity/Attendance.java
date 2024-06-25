package lk.ijse.drivingSchool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Attendance {
    private String lessonId;
    private String studentId;
    private String date;
    private String startTime;
    private String endTime;
    private String attendanceStatus;
}
