package activities;

import android.os.Bundle;

import com.buzz.yora.R;

import fragments.LoginFragment;

/**
 * Created by jorge on 01/11/2015.
 */
public class LoginNarrowActivity extends BaseActivity implements LoginFragment.Callbacks {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_narrow);
    }

    @Override
    public void onLoggedIn() {
        setResult(RESULT_OK);
        finish();
    }
}
