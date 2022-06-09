package uni_lj.fe.tunv.projekt.toot_orino;

import java.util.Date;

public class Timeslot {

    private User tutor;
    private User student;

    private Subject subject;
    private String details;
    private String desiredGrade;

    private Date startSate;
    private Date endSate;
    private String location;

    public Timeslot(User tutor, User student, Subject subject, String details, String desiredGrade, Date startSate, Date endSate, String location) {
        this.tutor = tutor;
        this.student = student;
        this.subject = subject;
        this.details = details;
        this.desiredGrade = desiredGrade;
        this.startSate = startSate;
        this.endSate = endSate;
        this.location = location;
    }

    public User getTutor() {
        return tutor;
    }

    public void setTutor(User tutor) {
        this.tutor = tutor;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
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

    public Date getStartSate() {
        return startSate;
    }

    public void setStartSate(Date startSate) {
        this.startSate = startSate;
    }

    public Date getEndSate() {
        return endSate;
    }

    public void setEndSate(Date endSate) {
        this.endSate = endSate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
