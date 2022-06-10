package uni_lj.fe.tunv.projekt.toot_orino;

import java.sql.Timestamp;
import java.util.Date;

public class Timeslot {

    private String tutorID;
    private String studentID;

    private Subject subject;
    private String details;
    private String desiredGrade;

    private Timestamp startTimeStamp;
    private Timestamp endTimeStamp;
    private String location;

    public Timeslot(String tutorID, String studentID, Subject subject, String details, String desiredGrade, Timestamp startTimeStamp, Timestamp endTimeStamp, String location) {
        this.tutorID = tutorID;
        this.studentID = studentID;
        this.subject = subject;
        this.details = details;
        this.desiredGrade = desiredGrade;
        this.startTimeStamp = startTimeStamp;
        this.endTimeStamp = endTimeStamp;
        this.location = location;
    }

    public String getTutorID() {
        return tutorID;
    }

    public void setTutorID(String tutorID) {
        this.tutorID = tutorID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDesiredGrade() {
        return desiredGrade;
    }

    public void setDesiredGrade(String desiredGrade) {
        this.desiredGrade = desiredGrade;
    }

    public Timestamp getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(Timestamp startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public Timestamp getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(Timestamp endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
