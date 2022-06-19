package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

        setAdapter();

        TextView switcher = findViewById(R.id.switch_tag_student);
        switcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityStudent.this, MainActivityTutor.class));
        });

        TextView menuSwitcher = findViewById(R.id.schedule_tag_student);
        menuSwitcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityStudent.this, ScheduleActivityStudent.class));
        });
    }

    private void setAdapter() {
        setOnClickListener();
        Timestamp test = new Timestamp(2000, 5, 6, 20, 20, 0, 0);
        ArrayList<Timeslot> timeslots = new ArrayList<Timeslot>();
        Timeslot timeslot1 = new Timeslot("00", "10", new Subject("Matematika", 5), "test1Details", "4", test, test, "Janezova ulica 5");
        Timeslot timeslot2 = new Timeslot("00", "10", new Subject("Biologija", 6), "test1Details", "4", test, test, "Janezova ulica 5");
        timeslots.add(timeslot1);
        timeslots.add(timeslot2);

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