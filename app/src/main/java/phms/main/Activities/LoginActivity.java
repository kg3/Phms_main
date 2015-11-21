package phms.main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import phms.main.R;

/**
 * Created by Cameron on 11/6/15.
 */
public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithParse(v);
            }
        });
    }

    private void loginWithParse(final View view) {
        ParseUser.logInInBackground(
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e != null)
                            Snackbar.make(view, "Invalid Login", Snackbar.LENGTH_SHORT).show();

                        else {
                            Intent i = new Intent(getBaseContext(), MenuMain.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                        }
                    }
                });
    }
}
