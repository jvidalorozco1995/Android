package activities;

import android.os.Bundle;

import com.buzz.yora.R;

import navigation.MainNavDrawer;

/**
 * Created by jorge on 01/11/2015.
 */
public class MainActivity extends BaseAuthenticatedActivity {

    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
         setContentView(R.layout.activity_main);
         getSupportActionBar().setTitle("Inbox");
         setNavDrawer(new MainNavDrawer(this));
    }
}
