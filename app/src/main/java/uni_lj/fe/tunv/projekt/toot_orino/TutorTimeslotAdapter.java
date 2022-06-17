package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

public class TutorTimeslotAdapter extends RecyclerView.Adapter<TutorTimeslotAdapter.ViewHolder> {
    private ArrayList<Timeslot> timeslots;

    public TutorTimeslotAdapter(ArrayList<Timeslot> timeslots){
        this.timeslots = timeslots;
    }

    @NonNull
    @Override
    public TutorTimeslotAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.tutor_timeslot_card, parent, false);
        TutorTimeslotAdapter.ViewHolder viewHolder = new TutorTimeslotAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameView.setText(timeslots.get(position).getTutorID());
        holder.subjectView.setText(timeslots.get(position).getSubject().getName());
        holder.dateView.setText(new StringBuilder().append(timeslots.get(position).getStartDate().getDate()).append(".").append(timeslots.get(position).getStartDate().getMonth()).toString());
        holder.timestampView.setText(timeslots.get(position).getStartDate().getHours() + ":" + timeslots.get(position).getStartDate().getMinutes());
    }
    @Override
    public int getItemCount() {
        return timeslots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView subjectView;
        public TextView dateView;
        public TextView timestampView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.nameView = (TextView) itemView.findViewById(R.id.tutorname_text);
            this.subjectView = (TextView) itemView.findViewById(R.id.subject_text);
            this.dateView = (TextView) itemView.findViewById(R.id.date_text);
            this.timestampView = (TextView) itemView.findViewById(R.id.timeslot_time_text);
        }
    }
}
