package uni_lj.fe.tunv.projekt.toot_orino.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import uni_lj.fe.tunv.projekt.toot_orino.Tutor.MainActivityTutor;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.Timeslot;
import uni_lj.fe.tunv.projekt.toot_orino.R;

public class MainActivityStudent extends AppCompatActivity{
    private StudentSearchAdapter.RecyclerClickListener listener;
    private DBAccess dba;

    float x1, x2, y1, y2;

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

        this.dba = new DBAccess();
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

    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if(x2 < x1){
                    Intent i = new Intent(MainActivityStudent.this, ScheduleActivityStudent.class);
                    startActivity (i);
                }
                break;
        }
        return false;
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
        this.dba.getTBR(User.getCurrentUserID(), new OnTimeslotsFilledListener() {
            @Override
            public void onTimeslotsFilled(ArrayList<Timeslot> timeslots) {
                setAdapter(timeslots);
            }

            @Override
            public void onError(Exception taskException) {
                Log.w(null, "Failed to load student timeslots");
            }
        });
    }
}