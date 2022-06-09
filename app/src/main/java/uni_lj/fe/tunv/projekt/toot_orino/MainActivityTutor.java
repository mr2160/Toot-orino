package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivityTutor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tutor);

        TextView switcher = findViewById(R.id.switch_tag_tutor);
        switcher.setOnClickListener(v -> {
            startActivity(new Intent(MainActivityTutor.this, MainActivityStudent.class));
        });

        /*User testUser = new User("Martin Rode", Arrays.asList(), "Študent FE", 9.5f, "Lorem ipsum kerjfn jsnfkj askdjfnasi fjsjd skdjfnk jdjdj asdf.", Arrays.asList(), Arrays.asList());
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Subjects").add(testUser).addOnSuccessListener(suc -> {
            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT);
        }).addOnFailureListener(err -> {
            Toast.makeText(this, "Error"+err, Toast.LENGTH_LONG);
        });*/
    }
}
