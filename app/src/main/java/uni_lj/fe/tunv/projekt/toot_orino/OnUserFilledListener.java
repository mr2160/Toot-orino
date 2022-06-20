package uni_lj.fe.tunv.projekt.toot_orino;

import uni_lj.fe.tunv.projekt.toot_orino.Objects.User;

public interface OnUserFilledListener {
    void onUserFilled(User user);
    void onError(Exception taskException);
}
