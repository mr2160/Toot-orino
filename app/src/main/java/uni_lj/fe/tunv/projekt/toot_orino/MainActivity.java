package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.Date;


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

        User.currentUserID = "yW8mv9udIL4Uzbfhb3pT";

        Timeslot t1 = new Timeslot("yW8mv9udIL4Uzbfhb3pT",
                                "Martin Rode",
                                "",
                                "",
                                new Subject("Java", 10),
                                "",
                                "",
                                new Date(java.sql.Timestamp.valueOf("2022-06-27 10:00:00.0").getTime()),
                                new Date(java.sql.Timestamp.valueOf("2022-06-27 12:00:00.0").getTime()),
                                "zoom");

        Timeslot t2 = new Timeslot("yW8mv9udIL4Uzbfhb3pT",
                "Martin Rode",
                "",
                "",
                new Subject("Math", 7),
                "",
                "",
                new Date(java.sql.Timestamp.valueOf("2022-06-27 13:00:00.0").getTime()),
                new Date(java.sql.Timestamp.valueOf("2022-06-27 14:00:00.0").getTime()),
                "zoom");

        Timeslot t3 = new Timeslot("gtilMNCYIyWbYV8gsJP8",
                "Jana Mokar",
                "",
                "",
                new Subject("English literature", 9),
                "",
                "",
                new Date(java.sql.Timestamp.valueOf("2022-06-29 08:00:00.0").getTime()),
                new Date(java.sql.Timestamp.valueOf("2022-06-29 09:00:00.0").getTime()),
                "zoom");
    }

}