package activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

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

        if(!isTablet){
            View textFields = findViewById(R.id.activity_profile_textFields);
            RelativeLayout.LayoutParams params =(RelativeLayout.LayoutParams)textFields.getLayoutParams();
            params.setMargins(0,params.getMarginStart(),0,0);
            params.removeRule(RelativeLayout.END_OF);
            params.addRule(RelativeLayout.BELOW,R.id.activity_profile_changeAvatar);
            textFields.setLayoutParams(params);
        }
    }
}
