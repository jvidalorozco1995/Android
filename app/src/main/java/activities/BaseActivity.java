package activities;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import infrastructure.YoraApplication;

/**
 * Created by jorge on 01/11/2015.
 */
public abstract class BaseActivity extends ActionBarActivity {
    protected YoraApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application =(YoraApplication)getApplication();
    }
}
