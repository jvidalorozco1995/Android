package activities;

import android.os.Bundle;

import com.buzz.yora.R;

import navigation.MainNavDrawer;

/**
 * Created by jorge on 03/01/2016.
 */
public class ProfileActivity extends BaseAuthenticatedActivity {
    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        setNavDrawer(new MainNavDrawer(this));
    }
}
