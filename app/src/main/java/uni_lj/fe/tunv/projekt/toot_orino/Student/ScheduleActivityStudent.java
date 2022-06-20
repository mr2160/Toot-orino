package uni_lj.fe.tunv.projekt.toot_orino.Student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import uni_lj.fe.tunv.projekt.toot_orino.Tutor.MainActivityTutor;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.Timeslot;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.User;
import uni_lj.fe.tunv.projekt.toot_orino.R;

public class ScheduleActivityStudent extends AppCompatActivity {
    private StudentScheduleAdapter.RecyclerClickListener listener;

    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_student);

        TextView switcher = findViewById(R.id.switch_tag_student);
        switcher.setOnClickListener(v -> {
            startActivity(new Intent(ScheduleActivityStudent.this, MainActivityTutor.class));
        });

        TextView menuSwitcher = findViewById(R.id.search_tag_student);
        menuSwitcher.setOnClickListener(v -> {
            startActivity(new Intent(ScheduleActivityStudent.this, MainActivityStudent.class));
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("Users").document(User.currentUserID);
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                setAdapter(user.getStudentTimeslots());
            }
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
                if(x1 < x2){
                    Intent i = new Intent(ScheduleActivityStudent.this, MainActivityStudent.class);
                    startActivity (i);
                }
                break;
        }
        return false;
    }

    private void setAdapter(ArrayList<Timeslot> timeslots) {
        setOnClickListener();


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.schedule_student_recycle_view);
        StudentScheduleAdapter STadapter = new StudentScheduleAdapter(timeslots, listener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(STadapter);
    }
    //For expanding box with aditional info
    private void setOnClickListener() {
        listener = new StudentScheduleAdapter.RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                View details = view.findViewById(R.id.details);
                Button cancelBtn = view.findViewById(R.id.cancel_timeslot);
                if(details.getVisibility() == View.GONE) {
                    details.setVisibility(View.VISIBLE);
                    cancelBtn.setVisibility(View.VISIBLE);
                }else{
                    details.setVisibility(View.GONE);
                    cancelBtn.setVisibility(View.GONE);
                }
            }
        };
    }

}