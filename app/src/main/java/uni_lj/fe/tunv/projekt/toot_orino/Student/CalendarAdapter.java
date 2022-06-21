package uni_lj.fe.tunv.projekt.toot_orino.Student;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import uni_lj.fe.tunv.projekt.toot_orino.R;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private String[] dates;
    private String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
    private String selectedDate;
    private MainActivityStudent activity;
    private int offset;

    public CalendarAdapter(String currentDate, MainActivityStudent activity){
        this.dates = getDates();
        this.selectedDate = currentDate;
        this.activity = activity;
        this.offset = 0;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.activity_calendar_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(dates[position].compareTo(this.selectedDate)==0){
            holder.itemView.setBackgroundResource(R.drawable.rounded_border_calendar);
        }if(dates[position].compareTo(this.selectedDate)!=0){
            holder.itemView.setBackgroundResource(0);
        }
        holder.dateView.setText(dates[position]);
        holder.dayView.setText(days[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedDate=dates[holder.getAdapterPosition()];
                notifyDataSetChanged();
                activity.loadTimeslots(selectedDate);
            }
        });
    }



    @Override
    public int getItemCount() {
        return dates.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dayView;
        public TextView dateView;
        public boolean active;
        public ViewHolder(View itemView) {
            super(itemView);
            this.dayView = (TextView) itemView.findViewById(R.id.day_text);
            this.dateView = (TextView) itemView.findViewById(R.id.date_text);
        }
    }

    public String[] getDates(){
        DateFormat format = new SimpleDateFormat("dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.add(Calendar.DAY_OF_MONTH, 7*this.offset);
        String[] dates = new String[7];
        for (int i = 0; i < 7; i++)
        {
            dates[i] = format.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return dates;
    }




    public void nextWeek(){
        if(this.offset > 2){
            return;
        }
        this.offset += 1;
        this.dates = getDates();
        notifyDataSetChanged();
    }

    public void previousWeek(){
        if(this.offset < -2){
            return;
        }
        this.offset -= 1;
        this.dates = getDates();
        notifyDataSetChanged();
        notifyDataSetChanged();
    }
}
