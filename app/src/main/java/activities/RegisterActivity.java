package activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.buzz.yora.R;

/**
 * Created by jorge on 02/01/2016.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText usernameText;
    private EditText emailText;
    private EditText passwordText;
    private Button registerButton;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acvity_register);

        usernameText =(EditText)findViewById(R.id.activity_register_userName);
        emailText =(EditText)findViewById(R.id.activity_register_Email);
        passwordText =(EditText)findViewById(R.id.activity_register_Password);
        registerButton =(Button)findViewById(R.id.activity_register_RegisterButton);
        progressBar = findViewById(R.id.activity_register_progressBar);

        registerButton.setOnClickListener(this);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
      if(v==registerButton){
          application.getAuth().getUser().setIsLoggedIn(true);
          setResult(RESULT_OK);
          finish();
      }
    }
}
