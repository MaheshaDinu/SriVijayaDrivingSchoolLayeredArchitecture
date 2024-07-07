package lk.ijse.drivingSchool.bo;

import lk.ijse.drivingSchool.bo.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getBoFactory(){return (boFactory == null)?boFactory = new BOFactory():boFactory;}
    public enum BOTypes{
        ADD_INSTRUCTOR,ADD_LESSON,ADD_PAYMENT,ADD_PRACTICAL_EXAM,ADD_VEHICLE,ADD_WRITTEN_EXAM,ATTENDANCE,DASHBOARD,EXAM,INSTRUCTOR,LESSON_SCHEDULE,LOGIN,PAYMENT,REGISTRATION,REMOVE_INSTRUCTOR,REMOVE_LESSON,REMOVE_PAYMENT,REMOVE_VEHICLE,STUDENT,STUDENT_EDIT,STUDENT_PROFILE,STUDENT_REGISTRATION,STUDENT_REMOVE,VEHICLE
    }
    public SuperBO getBO(BOTypes types){
        switch (types){
            case ADD_INSTRUCTOR:
                return new AddInstructorBOImpl();
            case ADD_LESSON:
                return new AddLessonBOImpl();
            case ADD_PAYMENT:
                return new AddPaymentBOImpl();
            case ADD_PRACTICAL_EXAM:
                return new AddPracticalExamBOImpl();
            case ADD_VEHICLE:
                return new AddVehicleBOImpl();
            case ADD_WRITTEN_EXAM:
                return new AddWrittenExamBOImpl();
            case ATTENDANCE:
                return new AttendanceBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
            case EXAM:
                return new ExamBOImpl();
            case INSTRUCTOR:
                return new InstructorBOImpl();
            case LESSON_SCHEDULE:
                return new LessonScheduleBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case REGISTRATION:
                return new RegistrationBOImpl();
            case REMOVE_INSTRUCTOR:
                return new RemoveInstructorBOImpl();
            case REMOVE_LESSON:
                return new RemoveLessonBOImpl();
            case REMOVE_PAYMENT:
                return new RemovePaymentBOImpl();
            case REMOVE_VEHICLE:
                return new RemoveVehicleBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case STUDENT_EDIT:
                return new StudentEditBOImpl();
            case STUDENT_PROFILE:
                return new StudentProfileBOImpl();
            case STUDENT_REMOVE:
                return new StudentRemoveBOImpl();
            case STUDENT_REGISTRATION:
                return new StudentRegistrationBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            default:
                return null;
        }
    }
}
