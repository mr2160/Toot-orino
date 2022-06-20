package uni_lj.fe.tunv.projekt.toot_orino.Tutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import uni_lj.fe.tunv.projekt.toot_orino.R;
import uni_lj.fe.tunv.projekt.toot_orino.Student.MainActivityStudent;

public class SchedulingActivityTutor extends AppCompatActivity {
    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling_tutor);

        TextView switcher = findViewById(R.id.switch_tag_tutor);
        switcher.setOnClickListener(v -> {
            startActivity(new Intent(SchedulingActivityTutor.this, MainActivityStudent.class));
        });

        TextView menuSwitcher = findViewById(R.id.activity_tag_tutor);
        menuSwitcher.setOnClickListener(v -> {
            startActivity(new Intent(SchedulingActivityTutor.this, MainActivityTutor.class));
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
                    Intent i = new Intent(SchedulingActivityTutor.this, MainActivityTutor.class);
                    startActivity (i);
                }
                break;
        }
        return false;
    }
}