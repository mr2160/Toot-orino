package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivityStudent extends AppCompatActivity{
    private StudentSearchAdapter.RecyclerClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        String[] dates = {"29", "1", "2", "3", "4", "5", "6"};
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.calendar_recycle_view);
        CalendarAdapter adapter = new CalendarAdapter(dates, days);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        loadTimeslots();

        TextView switcher = findViewById(R.id.switch_tag_student);
        switcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityStudent.this, MainActivityTutor.class));
        });

        TextView menuSwitcher = findViewById(R.id.schedule_tag_student);
        menuSwitcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityStudent.this, ScheduleActivityStudent.class));
        });
    }

    private void setAdapter(ArrayList<Timeslot> timeslots) {
        setOnClickListener();


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.timeslots_student_recycle_view);
        StudentSearchAdapter STadapter = new StudentSearchAdapter(timeslots, listener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(STadapter);
    }
    //For expanding box with aditional info
    private void setOnClickListener() {
        listener = new StudentSearchAdapter.RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                View details = view.findViewById(R.id.details_search);
                if(details.getVisibility() == View.GONE) {
                    details.setVisibility(View.VISIBLE);
                }else{
                    details.setVisibility(View.GONE);
                }
            }
        };
    }

    private void loadTimeslots(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Timeslots")
                .whereEqualTo("studentID", "")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Timeslot> tslts= new ArrayList<Timeslot>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Timeslot t = document.toObject(Timeslot.class);
                                tslts.add(t);
                            }
                            setAdapter(tslts);
                        }
                    }
                });
    }
}