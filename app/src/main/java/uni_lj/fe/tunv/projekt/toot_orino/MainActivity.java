package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import uni_lj.fe.tunv.projekt.toot_orino.Objects.Subject;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.Timeslot;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.User;
import uni_lj.fe.tunv.projekt.toot_orino.Student.MainActivityStudent;
import uni_lj.fe.tunv.projekt.toot_orino.Tutor.MainActivityTutor;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button studentButton = findViewById(R.id.student_mode_btn);
        studentButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MainActivityStudent.class));
        });

        Button tutorButton = findViewById(R.id.tutor_mode_btn);
        tutorButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MainActivityTutor.class));
        });






        User.currentUserID = "f4Y9rMCb3bzv6WDI7uxl";



        DBAccess dba = new DBAccess();
        dba.getUserFromId(User.getCurrentUserID(), new OnUserFilledListener() {
            @Override
            public void onUserFilled(User user) {
                User.currentUser = user;
            }
            @Override
            public void onError(Exception taskException) {
                Log.w(null, "Failed to get currentUser");
            }
        });

        /*Timeslot t1 = new Timeslot("f4Y9rMCb3bzv6WDI7uxl", //F1XHkhK8H7qykgdPCm5y
                                "F1XHkhK8H7qykgdPCm5y",
                                new Subject("Ples", 7),
                                "",
                                "",
                                new Date(java.sql.Timestamp.valueOf("2022-06-25 10:20:00.0").getTime()),
                                new Date(java.sql.Timestamp.valueOf("2022-06-25 12:20:00.0").getTime()),
                                "Monke 60",
                        false);

        dba.addTimeslot(t1);*/
    }

}