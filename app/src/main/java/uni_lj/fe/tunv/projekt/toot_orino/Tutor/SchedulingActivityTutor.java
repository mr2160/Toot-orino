package uni_lj.fe.tunv.projekt.toot_orino.Tutor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import uni_lj.fe.tunv.projekt.toot_orino.DBAccess;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.Subject;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.Timeslot;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.User;
import uni_lj.fe.tunv.projekt.toot_orino.OnUserFilledListener;
import uni_lj.fe.tunv.projekt.toot_orino.R;
import uni_lj.fe.tunv.projekt.toot_orino.Student.MainActivityStudent;

public class SchedulingActivityTutor extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    Button dateButton;
    Date currentDate = new Date();
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

        initDatePicker();
        dateButton = findViewById(R.id.date_pick_btn);
        dateButton.setText(getTodaysDate());

        //Need field for subject (object of String and hourly rate), location, start, end date, details

        TextView createTimeslot = findViewById(R.id.create_timeslot_button);
        createTimeslot.setOnClickListener(v -> {

            EditText subjectText = (EditText) findViewById(R.id.subject_name_input);
            String subject = subjectText.getText().toString();

            boolean doit = true;
            EditText hourlyRateText = (EditText) findViewById(R.id.hourly_rate_input);
            int hourlyRate = ((!hourlyRateText.getText().toString().isEmpty())) ? Integer.parseInt(hourlyRateText.getText().toString()) : -1;
            doit = hourlyRate<0 ? false : true;

            EditText locationText = (EditText) findViewById(R.id.location_input);
            String location = locationText.getText().toString();

            EditText hourStart = (EditText) findViewById(R.id.start_hour_input);
            int hourS = ((!hourStart.getText().toString().isEmpty())) ? Integer.parseInt(hourStart.getText().toString()) : -1;
            doit = hourS<0 ? false : true;

            EditText minutesStart = (EditText) findViewById(R.id.start_minutes_input);
            int minutesS = ((!minutesStart.getText().toString().isEmpty())) ? Integer.parseInt(minutesStart.getText().toString()) : -1;
            doit = minutesS<0 ? false : true;

            EditText detailText = (EditText) findViewById(R.id.details_input);
            String details = detailText.getText().toString();

            currentDate.setHours(hourS);
            currentDate.setMinutes(minutesS);

            Date startDate = currentDate;
            Date endDate = currentDate;
            endDate.setHours(hourS + 1);

            Timeslot newTimeslot = new Timeslot(User.currentUserID,
                    "",
                    new Subject(subject, hourlyRate),
                    details,
                    "",
                    startDate,
                    endDate,
                    location,
                    false);
            if (newTimeslot.getSubject().getName().isEmpty() || newTimeslot.getLocation().isEmpty() || doit == false){
                Log.w(null, "need more information to create timeslot");
            }else{
                Log.w(null, "Created timeslot");
                Log.w(null, String.valueOf(minutesS));
                DBAccess dba = new DBAccess();
                dba.addTimeslot(newTimeslot);
            }
        });
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Log.w(null, String.valueOf(currentDate.getYear()));
        month = month + 1;
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                currentDate.setYear(year);
                currentDate.setMonth(month);
                currentDate.setDate(day);
                Log.w(null, String.valueOf(currentDate.getYear()));
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year) {
        return day+" "+month+" "+ year;
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }


    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if(x1 < x2-8){
                    Intent i = new Intent(SchedulingActivityTutor.this, MainActivityTutor.class);
                    startActivity (i);
                }
                break;
        }
        return false;
    }

}