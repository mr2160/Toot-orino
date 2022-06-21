package uni_lj.fe.tunv.projekt.toot_orino.Tutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

import uni_lj.fe.tunv.projekt.toot_orino.DBAccess;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.Timeslot;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.User;
import uni_lj.fe.tunv.projekt.toot_orino.OnTimeslotsFilledListener;
import uni_lj.fe.tunv.projekt.toot_orino.R;
import uni_lj.fe.tunv.projekt.toot_orino.Student.MainActivityStudent;

public class MainActivityTutor extends AppCompatActivity{
    private TutorTimeslotAdapter.RecyclerClickListener listener;
    private DBAccess dba;

    float x1, x2, y1, y2;

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

        this.dba = new DBAccess();
        loadTimeslots();

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
                    Intent i = new Intent(MainActivityTutor.this, SchedulingActivityTutor.class);
                    startActivity (i);
                }
                break;
        }
        return false;
    }

    private void setAdapter(ArrayList<Timeslot> timeslots, ArrayList<String> timeslotIDs) {
        setOnClickListener();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.timeslots_tutor_recycle_view);
        TutorTimeslotAdapter TTadapter = new TutorTimeslotAdapter(timeslots, timeslotIDs, listener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(TTadapter);
    }
//For expanding box with aditional info
    private void setOnClickListener() {
        listener = new TutorTimeslotAdapter.RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                View details = view.findViewById(R.id.details_tutor);
                Button confirmBtn = view.findViewById(R.id.confirm_button);
                Button rejectBtn = view.findViewById(R.id.reject_button);
                if(details.getVisibility() == View.GONE) {
                    details.setVisibility(View.VISIBLE);
                    confirmBtn.setVisibility(View.VISIBLE);
                    rejectBtn.setVisibility(View.VISIBLE);
                }else{
                    details.setVisibility(View.GONE);
                    confirmBtn.setVisibility(View.GONE);
                    rejectBtn.setVisibility(View.GONE);
                }
            }
        };
    }

    private void loadTimeslots(){
        this.dba.getTBC(User.getCurrentUserID(), new OnTimeslotsFilledListener() {
            @Override
            public void onTimeslotsFilled(ArrayList<Timeslot> timeslots, ArrayList<String> timeslotIDs) {
                setAdapter(timeslots, timeslotIDs);
            }

            @Override
            public void onError(Exception taskException) {
                Log.w(null, "Failed to load student timeslots");
            }
        });
    }
}
