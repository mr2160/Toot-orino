package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivityTutor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tutor);

        TextView switcher = findViewById(R.id.switch_tag_tutor);
        switcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityTutor.this, MainActivityStudent.class));
        });

        //User testUser = new User("Jana Mokar", Arrays.asList(), "Gimnazija Bežigrad", 7.0f, "Lorem ipsum kerjfn jsnfkj askdjfnasi fjsjd skdjfnk jdjdj asdf.", Arrays.asList(), Arrays.asList());

        /*DBAccess dba = new DBAccess();
        dba.addSubjectToUser(new Subject("Java", 15), "yW8mv9udIL4Uzbfhb3pT").onSuccessTask(suc->{
            Log.i(null, suc.toString());
            return null;
        })*/


        //Timeslot testTimeslot = new Timeslot("", "", new Subject("Java", 15), "Asynchronous functions", "8/10", ,);
    }
}
