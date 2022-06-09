package uni_lj.fe.tunv.projekt.toot_orino;

import java.util.List;

public class User {

    private String name;

    private List<Subject> subjects;
    private String currentEducation;
    private float rating;
    private String bio;


    private List<Timeslot> tutorTimeslots;
    private List<Timeslot> studentTimeslots;

    public User(){

    }

    public User(String name, List<Subject> subjects, String currentEducation, float rating, String bio, List<Timeslot> tutorTimeslots, List<Timeslot> studentTimeslots) {
        this.name = name;
        this.subjects = subjects;
        this.currentEducation = currentEducation;
        this.rating = rating;
        this.bio = bio;
        this.tutorTimeslots = tutorTimeslots;
        this.studentTimeslots = studentTimeslots;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getCurrentEducation() {
        return currentEducation;
    }

    public void setCurrentEducation(String currentEducation) {
        this.currentEducation = currentEducation;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Timeslot> getTutorTimeslots() {
        return tutorTimeslots;
    }

    public void setTutorTimeslots(List<Timeslot> tutorTimeslots) {
        this.tutorTimeslots = tutorTimeslots;
    }

    public List<Timeslot> getStudentTimeslots() {
        return studentTimeslots;
    }

    public void setStudentTimeslots(List<Timeslot> studentTimeslots) {
        this.studentTimeslots = studentTimeslots;
    }
}
