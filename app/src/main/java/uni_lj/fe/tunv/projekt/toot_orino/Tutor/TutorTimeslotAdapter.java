package uni_lj.fe.tunv.projekt.toot_orino.Tutor;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import uni_lj.fe.tunv.projekt.toot_orino.DBAccess;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.Timeslot;
import uni_lj.fe.tunv.projekt.toot_orino.Objects.User;
import uni_lj.fe.tunv.projekt.toot_orino.OnUserFilledListener;
import uni_lj.fe.tunv.projekt.toot_orino.R;

public class TutorTimeslotAdapter extends RecyclerView.Adapter<TutorTimeslotAdapter.ViewHolder> {
    private ArrayList<Timeslot> timeslots;
    private ArrayList<String> timeslotIDs;
    private static RecyclerClickListener listener;
    private DBAccess dba;

    public TutorTimeslotAdapter(ArrayList<Timeslot> timeslots, ArrayList<String> timeslotIDs, RecyclerClickListener listener){
        this.timeslots = timeslots;
        this.listener = listener;
        this.timeslotIDs = timeslotIDs;
        this.dba = new DBAccess();
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

        if(!timeslots.get(position).getStudentID().isEmpty()){
            fillUserInfo(timeslots.get(position).getStudentID(), holder.nameView, holder.educationView);
        }
        holder.nameView.setText("No student");
        holder.educationView.setText("No student");
        holder.subjectView.setText(timeslots.get(position).getSubject().getName());
        holder.dateView.setText(new StringBuilder().append(timeslots.get(position).getStartDate().getDate()).append(".").append(timeslots.get(position).getStartDate().getMonth()).toString());
        String minutes = String.valueOf(timeslots.get(position).getStartDate().getMinutes());
        if(minutes.length()<2){
            minutes = "0" + minutes;
        }
        holder.timestampView.setText(timeslots.get(position).getStartDate().getHours() + ":" + minutes);
        holder.timeslotID = timeslotIDs.get(position);
        holder.locationView.setText((timeslots.get(position).getLocation()));
        if(!timeslots.get(position).getConfirmed() && !timeslots.get(position).getStudentID().isEmpty()) {
            holder.pendingView.setText("Pending");
        }
    }


    private void fillUserInfo(String id, TextView nameView, TextView educationView) {
        DBAccess dba = new DBAccess();
        dba.getUserFromId(id, new OnUserFilledListener() {
            @Override
            public void onUserFilled(User user) {
                if(user != null){
                    nameView.setText(user.getName());
                    educationView.setText(user.getCurrentEducation());
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
        public TextView nameView;
        public TextView subjectView;
        public TextView dateView;
        public TextView timestampView;
        public String timeslotID;
        public TextView locationView;
        public TextView educationView;
        public TextView pendingView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.nameView = (TextView) itemView.findViewById(R.id.studentname_text);
            this.subjectView = (TextView) itemView.findViewById(R.id.subject_text);
            this.dateView = (TextView) itemView.findViewById(R.id.date_text);
            this.timestampView = (TextView) itemView.findViewById(R.id.timeslot_time_text);
            this.locationView = (TextView) itemView.findViewById(R.id.location_text);
            this.educationView = (TextView) itemView.findViewById(R.id.education_text);
            this.pendingView =(TextView) itemView.findViewById(R.id.pending_text);
            itemView.setOnClickListener(this);
            itemView.findViewById(R.id.confirm_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     //Code for confirming request
                    DBAccess dba = new DBAccess();
                    dba.confirmTimeslot(timeslotID).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            itemView.findViewById(R.id.reject_button).setVisibility(View.GONE);
                            itemView.findViewById(R.id.confirm_button).setVisibility(View.GONE);
                            ((TextView) itemView.findViewById(R.id.pending_text)).setText("");
                        }
                    });
                }
            });
            itemView.findViewById(R.id.reject_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Code for rejecting request
                    DBAccess dba = new DBAccess();
                    dba.rejectTimeslot(timeslotID).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            itemView.findViewById(R.id.reject_button).setVisibility(View.GONE);
                            itemView.findViewById(R.id.confirm_button).setVisibility(View.GONE);
                            ((TextView) itemView.findViewById(R.id.pending_text)).setText("");
                            ((TextView) itemView.findViewById(R.id.studentname_text)).setText("No student");
                            ((TextView) itemView.findViewById(R.id.education_text)).setText("No student");
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
