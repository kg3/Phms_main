package phms.main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import phms.main.R;

/**
 *
 */
public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword,etAge,etHeight,etWeight,etPhoneNumber,etPhysicalAddress,etGender,etPhysician;
    EditText etPhysicianPhone, etPhysicianEmail, etEmergencyContact,etEmergencyContactPhone, etEmergencyContactEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etAge = (EditText) findViewById(R.id.etAge);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etPhysicalAddress = (EditText) findViewById(R.id.etPhysicalAddress);
        etGender = (EditText) findViewById(R.id.etGender);
        etPhysician = (EditText) findViewById(R.id.etPhysician);
        etPhysicianPhone = (EditText) findViewById(R.id.etPhysicianPhone);
        etPhysicianEmail = (EditText) findViewById(R.id.etPhysicianEmail);
        etEmergencyContact = (EditText) findViewById(R.id.etEmergencyContact);
        etEmergencyContactPhone = (EditText) findViewById(R.id.etEmergencyContactPhone);
        etEmergencyContactEmail = (EditText) findViewById(R.id.etEmergencyContactEmail);

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
        user.put("age", etAge.getText().toString());
        user.put("height", etHeight.getText().toString());
        user.put("weight", etWeight.getText().toString());
        user.put("name", etName.getText().toString());
        user.put("phoneNumber", etPhoneNumber.getText().toString());
        user.put("physicalAddress", etPhysicalAddress.getText().toString());
        user.put("gender", etGender.getText().toString());
        user.put("physician", etPhysician.getText().toString());
        user.put("physicianPhone", etPhysicianPhone.getText().toString());
        user.put("physicianEmail", etPhysicianEmail.getText().toString());
        user.put("emergencyContact", etEmergencyContact.getText().toString());
        user.put("emergencyContactPhone", etEmergencyContactPhone.getText().toString());
        user.put("emergencyContactEmail", etEmergencyContactEmail.getText().toString());


        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    //e.getMessage();

                    //Snackbar.make(view, doErrors(e), Snackbar.LENGTH_SHORT).show();
                    Snackbar.make(view, e.getMessage().toString(), Snackbar.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(getBaseContext(), MenuMain.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    //  i.putExtra(MenuMain.FLASH_WELCOME, true);
                    startActivity(i);
                }
            }
        });
    }

//    private String doErrors(ParseException e) {
//        switch (e.getCode()) {
//            case ParseException.USERNAME_TAKEN:
//            case ParseException.EMAIL_TAKEN:
//                return "Email already in use";
//            default:
//                return e.getMessage().toString(); //return "oops!";
//        }
//    }
}
