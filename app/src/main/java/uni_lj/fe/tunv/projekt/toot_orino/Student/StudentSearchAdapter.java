package uni_lj.fe.tunv.projekt.toot_orino.Student;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import uni_lj.fe.tunv.projekt.toot_orino.DBAccess;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.Timeslot;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.User;
import uni_lj.fe.tunv.projekt.toot_orino.R;

public class StudentSearchAdapter extends RecyclerView.Adapter<StudentSearchAdapter.ViewHolder> {
    private ArrayList<Timeslot> timeslots;
    private static StudentSearchAdapter.RecyclerClickListener listener;
    private ArrayList<String> timeslotIDs;
    private DBAccess dba;

    public StudentSearchAdapter(ArrayList<Timeslot> timeslots, ArrayList<String> timeslotIDs, StudentSearchAdapter.RecyclerClickListener listener){
        this.timeslots = timeslots;
        this.listener = listener;
        this.timeslotIDs = timeslotIDs;
    }


    @NonNull
    @Override
    public StudentSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.student_timeslot_search_card, parent, false);
        StudentSearchAdapter.ViewHolder viewHolder = new StudentSearchAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentSearchAdapter.ViewHolder holder, int position) {
        holder.nameView.setText(timeslots.get(position).getStudentID());
        holder.subjectView.setText(timeslots.get(position).getSubject().getName());
        holder.hourlyrateView.setText(new StringBuilder().append("$").append(timeslots.get(position).getSubject().getHourlyRate()).append("/h"));
        holder.dateView.setText(new StringBuilder().append(timeslots.get(position).getStartDate().getDate()).append(".").append(timeslots.get(position).getStartDate().getMonth()).toString());
        holder.timestampView.setText(timeslots.get(position).getStartDate().getHours() + ":" + timeslots.get(position).getStartDate().getMinutes());
        holder.locationView.setText(timeslots.get(position).getLocation());
        holder.timeslotID = timeslotIDs.get(position);
    }
    @Override
    public int getItemCount() {
        return timeslots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameView;
        public TextView subjectView;
        public TextView hourlyrateView;
        public TextView dateView;
        public TextView timestampView;
        public TextView locationView;
        public String timeslotID;

        public ViewHolder(View itemView) {
            super(itemView);
            this.nameView = (TextView) itemView.findViewById(R.id.tutorname_text);
            this.subjectView = (TextView) itemView.findViewById(R.id.subject_text);
            this.hourlyrateView = (TextView) itemView.findViewById(R.id.hourly_rate);
            this.dateView = (TextView) itemView.findViewById(R.id.date_text);
            this.timestampView = (TextView) itemView.findViewById(R.id.timeslot_time_text);
            this.locationView = (TextView) itemView.findViewById(R.id.location_text);
            itemView.setOnClickListener(this);
            itemView.findViewById(R.id.request_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DBAccess dba = new DBAccess();
                    dba.requestTimeslot(timeslotID, User.currentUserID, "", "").addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            itemView.setVisibility(View.GONE);
                        }
                    });
                }
            });
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerClickListener{
        void onClick(View view, int position);
    }
}
