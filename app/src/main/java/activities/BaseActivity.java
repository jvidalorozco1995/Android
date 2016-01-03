package activities;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import com.buzz.yora.R;

import infrastructure.YoraApplication;
import navigation.NavDrawer;

/**
 * Created by jorge on 01/11/2015.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected YoraApplication application;
    protected Toolbar toolbar;
    protected NavDrawer navDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application =(YoraApplication)getApplication();
    }


    @Override
    public void setContentView(@LayoutRes int layoutResId) {
        super.setContentView(layoutResId);

        toolbar =(Toolbar)findViewById(R.id.include_login_header);
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
    }
    protected void setNavDrawer(NavDrawer drawer){
        this.navDrawer = navDrawer;
        this.navDrawer.create();

    }
}
