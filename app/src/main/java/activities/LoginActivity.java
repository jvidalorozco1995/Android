package activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.buzz.yora.R;

/**
 * Created by jorge on 01/11/2015.
 */
public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void doLogin(View view) {
        application.getAuth().getUser().setIsLoggedIn(true);
        startActivity(new Intent (this,MainActivity.class));
        finish();
        //aca
    }
}
