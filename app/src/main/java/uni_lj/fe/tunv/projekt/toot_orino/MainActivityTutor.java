package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivityTutor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tutor);

        TextView switcher = findViewById(R.id.switch_tag_tutor);
        switcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityTutor.this, MainActivityStudent.class));
        });


        Timestamp test = new Timestamp(2000, 5, 5, 20, 20, 0, 0);
        Timeslot[] timeslots = new Timeslot[1];
        timeslots[0] = new Timeslot("00", "10", new Subject("Matematika", 5), "test1Details", "4", test, test, "Janezova ulica 5");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.timeslots_tutor_recycle_view);
        TutorTimeslotAdapter TTadapter = new TutorTimeslotAdapter(timeslots);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(TTadapter);


        DBAccess dba = new DBAccess();


        //----- ADD USER EXAMPLE --------

        //User testUser = new User("Jana Mokar", Arrays.asList(), "Gimnazija Bežigrad", 7.0f, "Lorem ipsum kerjfn jsnfkj askdjfnasi fjsjd skdjfnk jdjdj asdf.", Arrays.asList(), Arrays.asList());


        /*dba.addSubjectToUser(new Subject("Java", 15), "yW8mv9udIL4Uzbfhb3pT").onSuccessTask(suc->{
            Log.i(null, suc.toString());
            return null;
        })*/


        /*Timeslot testTimeslot = new Timeslot("yW8mv9udIL4Uzbfhb3pT",
                                            "gtilMNCYIyWbYV8gsJP8",
                                            new Subject("Java", 15),
                                            "Asynchronous functions",
                                            "8/10",
                                            new Date(java.sql.Timestamp.valueOf("2022-06-13 10:00:00.0").getTime()),
                                            new Date(java.sql.Timestamp.valueOf("2022-06-13 10:00:00.0").getTime()),
                                            "Zoom");

        //----- ADD TIMESLOT EXAMPLE --------
        dba.addTimeslot(testTimeslot).onSuccessTask(suc -> {
            Log.i(null,"POSLANO!");
            return null;
        }).onSuccessTask(err -> {
            Log.e(null, "NI ŠLO!");
            return null;
        });*/
    }
}
