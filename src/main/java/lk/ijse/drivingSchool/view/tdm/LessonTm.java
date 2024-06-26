package lk.ijse.drivingSchool.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class LessonTm {
    String date;
    String time;
    String instructor;
    String vehicle;
}
