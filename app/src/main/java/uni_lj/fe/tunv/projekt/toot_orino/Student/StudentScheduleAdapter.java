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
import uni_lj.fe.tunv.projekt.toot_orino.OnUserFilledListener;
import uni_lj.fe.tunv.projekt.toot_orino.R;

public class StudentScheduleAdapter extends RecyclerView.Adapter<StudentScheduleAdapter.ViewHolder> {
    private ArrayList<Timeslot> timeslots;
    private ArrayList<String> timeslotIDs;
    private static StudentScheduleAdapter.RecyclerClickListener listener;

    public StudentScheduleAdapter(ArrayList<Timeslot> timeslots, ArrayList<String> timeslotIDs, StudentScheduleAdapter.RecyclerClickListener listener){
        this.timeslots = timeslots;
        this.listener = listener;
        this.timeslotIDs = timeslotIDs;
    }


    @NonNull
    @Override
    public StudentScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.student_schedule_card, parent, false);
        StudentScheduleAdapter.ViewHolder viewHolder = new StudentScheduleAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentScheduleAdapter.ViewHolder holder, int position) {
        fillUserInfo(timeslots.get(position).getTutorID(), holder.nameView);
        holder.subjectView.setText(timeslots.get(position).getSubject().getName());
        holder.locationView.setText(timeslots.get(position).getLocation());
        holder.dateView.setText(new StringBuilder().append(timeslots.get(position).getStartDate().getDate()).append(".").append(timeslots.get(position).getStartDate().getMonth()).toString());
        String minutes = String.valueOf(timeslots.get(position).getStartDate().getMinutes());
        if(minutes.length()<2){
            minutes = "0" + minutes;
        }
        holder.timestampView.setText(timeslots.get(position).getStartDate().getHours() + ":" + minutes);
        holder.hourlyrateView.setText(new StringBuilder().append("$").append(timeslots.get(position).getSubject().getHourlyRate()).append("/h"));
        holder.timeslotID = timeslotIDs.get(position);
    }

    private void fillUserInfo(String id, TextView nameView) {
        DBAccess dba = new DBAccess();
        dba.getUserFromId(id, new OnUserFilledListener() {
            @Override
            public void onUserFilled(User user) {
                if(user != null){
                    nameView.setText(user.getName());
                }
            }
            @Override
            public void onError(Exception taskException) {
                Log.w(null, "Failed to get user");
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeslots.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView subjectView;
        public TextView locationView;
        public TextView dateView;
        public TextView timestampView;
        public TextView nameView;
        public TextView hourlyrateView;

        public String timeslotID;


        public ViewHolder(View itemView) {
            super(itemView);
            this.subjectView = (TextView) itemView.findViewById(R.id.subject_text);
            this.locationView = (TextView) itemView.findViewById(R.id.location_text);
            this.dateView = (TextView) itemView.findViewById(R.id.date_text);
            this.timestampView = (TextView) itemView.findViewById(R.id.timeslot_time_text);
            this.nameView = (TextView) itemView.findViewById(R.id.tutorname_text); //TODO: Get actual name from ID
            this.hourlyrateView = (TextView) itemView.findViewById(R.id.hourly_rate);
            itemView.setOnClickListener(this);
            /*itemView.findViewById(R.id.cancel_timeslot).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBAccess dba = new DBAccess();
                    dba.studentCancelTimeslot(timeslotID).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            itemView.setVisibility(View.GONE);
                        }
                    });
                }
            });*/
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
