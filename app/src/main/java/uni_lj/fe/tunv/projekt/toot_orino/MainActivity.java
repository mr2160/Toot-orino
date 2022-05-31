package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button studentButton = findViewById(R.id.student_mode_btn);
        studentButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MainActivityStudent.class));
        });

        Button tutorButton = findViewById(R.id.tutor_mode_btn);
        tutorButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MainActivityTutor.class));
        });
    }

}