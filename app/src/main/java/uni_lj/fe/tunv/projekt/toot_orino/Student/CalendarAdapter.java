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

import uni_lj.fe.tunv.projekt.toot_orino.R;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private String[] dates;
    private String[] days;
    private String selectedDate;
    private MainActivityStudent activity;

    public CalendarAdapter(String[] dates, String[] days, String currentDate, MainActivityStudent activity){
        this.dates = dates;
        this.days = days;
        this.selectedDate = currentDate;
        this.activity = activity;
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
}
