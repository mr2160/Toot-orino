package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SchedulingActivityTutor extends AppCompatActivity {

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
}