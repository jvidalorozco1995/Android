package infrastructure;

import android.content.Context;

/**
 * Created by jorge on 01/11/2015.
 */
public class Auth {
    public final Context context;
    private User user;

    public Auth(Context context){
        this.context = context;
        user = new User();
    }

    public User getUser(){
        return user;
    }
}
