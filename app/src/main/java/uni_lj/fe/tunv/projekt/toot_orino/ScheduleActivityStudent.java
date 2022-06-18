package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ScheduleActivityStudent extends AppCompatActivity {

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

    }
}