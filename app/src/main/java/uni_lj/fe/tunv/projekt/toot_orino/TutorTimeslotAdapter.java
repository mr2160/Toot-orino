package uni_lj.fe.tunv.projekt.toot_orino;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TutorTimeslotAdapter extends RecyclerView.Adapter<TutorTimeslotAdapter.ViewHolder> {
    private Timeslot[] timeslots;

    public TutorTimeslotAdapter(Timeslot[] timeslots){
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
        holder.subjectView.setText(timeslots[position].getSubject().getName());
        holder.timestampView.setText(timeslots[position].getStartDate().toString());
    }
    @Override
    public int getItemCount() {
        return timeslots.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView subjectView;
        public TextView timestampView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.subjectView = (TextView) itemView.findViewById(R.id.subject_text);
            this.timestampView = (TextView) itemView.findViewById(R.id.timeslot_date);
        }
    }
}
