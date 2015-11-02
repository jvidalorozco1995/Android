package activities;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jorge on 01/11/2015.
 */
public abstract class BaseAuthenticatedActivity extends BaseActivity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!application.getAuth().getUser().isLoggedIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        onYoraCreate(savedInstanceState);
    }

    protected abstract void onYoraCreate(Bundle savedInstanceState);

}
