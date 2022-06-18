package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;

public class MainActivityTutor extends AppCompatActivity{
    private TutorTimeslotAdapter.RecyclerClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tutor);

        TextView switcher = findViewById(R.id.switch_tag_tutor);
        switcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityTutor.this, MainActivityStudent.class));
        });

        TextView menuSwitcher = findViewById(R.id.scheduling_tag_tutor);
        menuSwitcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityTutor.this, SchedulingActivityTutor.class));
        });

        DBAccess dba = new DBAccess();

        setAdapter();

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

    private void setAdapter() {
        setOnClickListener();
        Timestamp test = new Timestamp(2000, 5, 6, 20, 20, 0, 0);
        ArrayList<Timeslot> timeslots = new ArrayList<Timeslot>();
        Timeslot timeslot1 = new Timeslot("00", "10", new Subject("Matematika", 5), "test1Details", "4", test, test, "Janezova ulica 5");
        Timeslot timeslot2 = new Timeslot("00", "10", new Subject("Biologija", 6), "test1Details", "4", test, test, "Janezova ulica 5");
        timeslots.add(timeslot1);
        timeslots.add(timeslot2);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.timeslots_tutor_recycle_view);
        TutorTimeslotAdapter TTadapter = new TutorTimeslotAdapter(timeslots, listener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(TTadapter);
    }
//For expanding box with aditional info
    private void setOnClickListener() {
        listener = new TutorTimeslotAdapter.RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView locationView = view.findViewById(R.id.location_text);
                if(locationView.getVisibility() == View.GONE) {
                    locationView.setVisibility(View.VISIBLE);
                }else{
                    locationView.setVisibility(View.GONE);
                }
            }
        };
    }
}
