package lk.ijse.drivingSchool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AttendanceDTO {
    private String lessonId;
    private String studentId;
    private String date;
    private String startTime;
    private String endTime;
    private String attendanceStatus;
}
