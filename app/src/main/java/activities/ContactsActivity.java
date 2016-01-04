package activities;

import android.os.Bundle;

import com.buzz.yora.R;

import navigation.MainNavDrawer;

/**
 * Created by jorge on 03/01/2016.
 */
public class ContactsActivity extends BaseAuthenticatedActivity {
    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contacts);
        setNavDrawer(new MainNavDrawer(this));
    }
}
