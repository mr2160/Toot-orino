package uni_lj.fe.tunv.projekt.toot_orino.Objects;

import java.util.Date;

public class Timeslot {

    private String tutorID;
    private String studentID;

    private Subject subject;
    private String details;
    private String desiredGrade;

    private Date startDate;
    private Date endDate;
    private String location;

    private Boolean confirmed;

    public Timeslot(){}

    public Timeslot(String tutorID, String studentID, Subject subject, String details, String desiredGrade, Date startDate, Date endDate, String location, Boolean confirmed) {
        this.tutorID = tutorID;
        this.studentID = studentID;
        this.subject = subject;
        this.details = details;
        this.desiredGrade = desiredGrade;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.confirmed = confirmed;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }
}
