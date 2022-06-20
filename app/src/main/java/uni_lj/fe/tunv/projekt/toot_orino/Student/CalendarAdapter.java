package uni_lj.fe.tunv.projekt.toot_orino.Student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import uni_lj.fe.tunv.projekt.toot_orino.R;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private String[] dates;
    private String[] days;

    public CalendarAdapter(String[] dates, String[] days){
        this.dates = dates;
        this.days = days;
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

        holder.dateView.setText(dates[position]);
        holder.dayView.setText(days[position]);
    }

    @Override
    public int getItemCount() {
        return dates.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dayView;
        public TextView dateView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.dayView = (TextView) itemView.findViewById(R.id.day_text);
            this.dateView = (TextView) itemView.findViewById(R.id.date_text);
        }
    }
}
