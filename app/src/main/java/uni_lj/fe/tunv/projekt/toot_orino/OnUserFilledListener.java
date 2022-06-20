package uni_lj.fe.tunv.projekt.toot_orino;

public interface OnUserFilledListener {
    void onUserFilled(User user);
    void onError(Exception taskException);
}
