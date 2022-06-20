package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import uni_lj.fe.tunv.projekt.toot_orino.Objects.Subject;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.Timeslot;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.User;

public class DBAccess {
    private FirebaseFirestore db;
    public DBAccess(){
        db = FirebaseFirestore.getInstance();
    }

    private ArrayList<Timeslot> resultTS;


    public Task<DocumentReference> addNewUser(User user){
        return this.db.collection("Users").add(user);
    }

    public Task<Void> addSubjectToUser(Subject subject, String userID){
        DocumentReference uref = db.collection("Users").document(userID);
        return uref.update("subjects", FieldValue.arrayUnion(subject));
    }

    public Task<DocumentReference> addTimeslot(Timeslot timeslot){
        Task<DocumentReference> addTask = this.db.collection("Timeslots").add(timeslot);
        return addTask;
    }

    public Task<DocumentSnapshot> getUserById(String userId){
        DocumentReference userRef = db.collection("Users").document(userId);
        return userRef.get();
    }


    public ArrayList<Timeslot> getTutorTimeslotsFromUser(String userId, OnTimeslotsFilledListener listener){
        ArrayList<Timeslot> timeslots = new ArrayList<Timeslot>();
        this.db.collection("Timeslots").whereEqualTo("tutorID", userId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Timeslot t = document.toObject(Timeslot.class);
                                timeslots.add(t);
                            }
                            listener.onTimeslotsFilled(timeslots);
                        }
                    }
                });
        return timeslots;
    }


    public void getStudentTimeslotsFromUser(String userId, OnTimeslotsFilledListener listener){
        ArrayList<Timeslot> timeslots = new ArrayList<Timeslot>();
        this.db.collection("Timeslots").whereEqualTo("studentID", userId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Timeslot t = document.toObject(Timeslot.class);
                                timeslots.add(t);
                            }
                            listener.onTimeslotsFilled(timeslots);
                        }
                    }
                });
    }

    //Get Timeslots to be confirmed
    public void getTBC(String userId, OnTimeslotsFilledListener listener){
        ArrayList<Timeslot> timeslots = new ArrayList<Timeslot>();
        this.db.collection("Timeslots").whereEqualTo("tutorID", userId)
                .whereEqualTo("confirmed", false)
                .whereNotEqualTo("studentID", "")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Timeslot t = document.toObject(Timeslot.class);
                                timeslots.add(t);
                            }
                            listener.onTimeslotsFilled(timeslots);
                        }
                    }
                });
    }

    //Get Timeslots to be requested
    public void getTBR(String userId, OnTimeslotsFilledListener listener){
        ArrayList<Timeslot> timeslots = new ArrayList<Timeslot>();
        this.db.collection("Timeslots")
                .whereEqualTo("studentID", "")
                .whereNotEqualTo("tutorID", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Timeslot t = document.toObject(Timeslot.class);
                                timeslots.add(t);
                            }
                            listener.onTimeslotsFilled(timeslots);
                        }
                    }
                });
    }

    public void getUserFromId(String userId, OnUserFilledListener listener) {
        this.db.collection("Users")
                .document(userId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User user = documentSnapshot.toObject(User.class);
                        listener.onUserFilled(user);
                    }
                });
    }
}

