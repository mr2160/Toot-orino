package uni_lj.fe.tunv.projekt.toot_orino.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import uni_lj.fe.tunv.projekt.toot_orino.DBAccess;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.User;
import uni_lj.fe.tunv.projekt.toot_orino.OnTimeslotsFilledListener;
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



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.calendar_recycle_view);
        CalendarAdapter adapter = new CalendarAdapter(getCurrentDate(), this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        this.dba = new DBAccess();
        loadTimeslots(getCurrentDate());

        TextView switcher = findViewById(R.id.switch_tag_student);
        switcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityStudent.this, MainActivityTutor.class));
        });

        TextView menuSwitcher = findViewById(R.id.schedule_tag_student);
        menuSwitcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityStudent.this, ScheduleActivityStudent.class));
        });

        ImageView rightArrow = findViewById(R.id.right_arrow);
        rightArrow.setOnClickListener(v -> {
            adapter.nextWeek();
        });

        ImageView leftArrow = findViewById(R.id.left_arrow);
        leftArrow.setOnClickListener(v -> {
            adapter.previousWeek();
        });

    }

    public String getCurrentDate(){
        DateFormat format = new SimpleDateFormat("dd");
        Calendar calendar = Calendar.getInstance();
        return format.format(calendar.getTime());
    }


    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if(x2 < x1-8){
                    Intent i = new Intent(MainActivityStudent.this, ScheduleActivityStudent.class);
                    startActivity (i);
                }
                break;
        }
        return false;
    }

    private void setAdapter(ArrayList<Timeslot> timeslots, ArrayList<String> timeslotIDs) {
        setOnClickListener();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.timeslots_student_recycle_view);
        StudentSearchAdapter STadapter = new StudentSearchAdapter(timeslots, timeslotIDs, listener);
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


    public void loadTimeslots(String date){
        this.dba.getTBR(User.getCurrentUserID(), new OnTimeslotsFilledListener() {
            @Override
            public void onTimeslotsFilled(ArrayList<Timeslot> timeslots, ArrayList<String> timeslotIDs) {
                DateFormat format = new SimpleDateFormat("dd");
                ArrayList<Timeslot> filteredTimeslots = new ArrayList<Timeslot>();
                for(int i = 0; i < timeslots.size(); i++){

                    String TSdate = format.format(timeslots.get(i).getStartDate().getTime());
                    Log.i(null, date+TSdate);
                    if(TSdate.compareTo(date)==0){
                        filteredTimeslots.add(timeslots.get(i));
                    }
                    Log.w(null, "timeslots size:" + String.valueOf(timeslots.size()));
                }
                setAdapter(filteredTimeslots, timeslotIDs);
            }

            @Override
            public void onError(Exception taskException) {
                Log.w(null, "Failed to load student timeslots");
            }
        });
    }
}