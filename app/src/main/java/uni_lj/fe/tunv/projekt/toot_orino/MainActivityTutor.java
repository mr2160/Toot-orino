package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ArrayList;

public class MainActivityTutor extends AppCompatActivity{
    private TutorTimeslotAdapter.RecyclerClickListener listener;
    private DBAccess dba;
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

    private void setAdapter(ArrayList<Timeslot> timeslots) {
        setOnClickListener();

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
