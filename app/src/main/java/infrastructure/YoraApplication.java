package infrastructure;

import android.app.Application;

/**
 * Created by jorge on 01/11/2015.
 */
public class YoraApplication extends Application {
    private Auth auth;

    @Override
    public void onCreate() {
        super.onCreate();
        auth = new Auth(this);
    }

    public Auth getAuth(){
        return auth;
    }
}
