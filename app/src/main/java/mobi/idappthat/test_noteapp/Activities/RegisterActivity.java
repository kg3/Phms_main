package mobi.idappthat.test_noteapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import mobi.idappthat.test_noteapp.R;

/**
 * Created by Cameron on 11/5/15.
 */
public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerWithParse(v);
            }
        });
    }

    private void registerWithParse(final View view) {
        ParseUser user = new ParseUser();
        user.setEmail(etEmail.getText().toString());
        user.setUsername(etEmail.getText().toString());
        user.setPassword(etPassword.getText().toString());
        user.put("name", etName.getText().toString());



        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Snackbar.make(view, generateErrorMessage(e.getCode()), Snackbar.LENGTH_SHORT).show();
                    Log.e("Parse", e.toString());
                } else {
                    Intent i = new Intent(getBaseContext(), MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.putExtra(MainActivity.FLASH_WELCOME, true);
                    startActivity(i);
                }
            }
        });
    }

    private String generateErrorMessage(int errorCode) {
        switch(errorCode) {
            case ParseException.EMAIL_TAKEN:
            case ParseException.USERNAME_TAKEN:
                return "Email already exists";

            default:
                return "Oops!";
        }
    }
}
