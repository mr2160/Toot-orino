package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivityStudent extends AppCompatActivity{

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

        TextView switcher = findViewById(R.id.switch_tag_student);
        switcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityStudent.this, MainActivityTutor.class));
        });

        TextView menuSwitcher = findViewById(R.id.schedule_tag_student);
        menuSwitcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityStudent.this, ScheduleActivityStudent.class));
        });
    }
}