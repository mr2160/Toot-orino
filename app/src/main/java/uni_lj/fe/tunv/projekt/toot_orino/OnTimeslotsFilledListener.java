package uni_lj.fe.tunv.projekt.toot_orino;

import java.util.ArrayList;

import uni_lj.fe.tunv.projekt.toot_orino.Objects.Timeslot;

public interface OnTimeslotsFilledListener {
    void onTimeslotsFilled(ArrayList<Timeslot> timeslots);
    void onError(Exception taskException);
}
