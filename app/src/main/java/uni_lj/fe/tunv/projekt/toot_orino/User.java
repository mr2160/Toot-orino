package uni_lj.fe.tunv.projekt.toot_orino;

import java.util.List;
import java.util.ArrayList;


public class User {

    public static String currentUserID;
    public static User currentUser;

    private String name;

    private List<Subject> subjects;
    private String currentEducation;
    private float rating;
    private String bio;

    public User(){}

    public User(String name, List<Subject> subjects, String currentEducation, float rating, String bio) {
        this.name = name;
        this.subjects = subjects;
        this.currentEducation = currentEducation;
        this.rating = rating;
        this.bio = bio;
    }

    public static String getCurrentUserID() {
        return currentUserID;
    }

    public static void setCurrentUserID(String currentUserID) {
        User.currentUserID = currentUserID;
    }

    public static User getCurrentUser() {
        return currentUser;
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
}
