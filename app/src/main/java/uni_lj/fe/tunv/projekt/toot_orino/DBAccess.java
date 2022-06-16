package uni_lj.fe.tunv.projekt.toot_orino;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class DBAccess {
    private FirebaseFirestore db;
    public DBAccess(){
        db = FirebaseFirestore.getInstance();
    }

    public Task<DocumentReference> addNewUser(User user){
        return this.db.collection("Users").add(user);
    }

    public Task<Void> addSubjectToUser(Subject subject, String userID){
        DocumentReference uref = db.collection("Users").document(userID);
        return uref.update("subjects", FieldValue.arrayUnion(subject));
    }

    public Task<Void> addTimeslot(Timeslot timeslot){
        Task<DocumentReference> addTask = this.db.collection("Timeslots").add(timeslot);

        DocumentReference tutorRef = db. collection("Users").document(timeslot.getTutorID());
        DocumentReference studentRef = db. collection("Users").document(timeslot.getStudentID());

        Task<Void> tutorTask = tutorRef.update("tutorTimeslots", FieldValue.arrayUnion(timeslot));
        Task<Void> studentTask = studentRef.update("studentTimeslots", FieldValue.arrayUnion(timeslot));

        return Tasks.whenAll(addTask, tutorTask, studentTask);
    }

    public Task<DocumentSnapshot> getUserById(String userId){
        DocumentReference userRef = db.collection("Users").document(userId);
        return userRef.get();
    }
}
