package phms.main.Activities;



import android.content.Intent;



import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.parse.Parse;
import com.parse.ParseUser;

import phms.main.R;

public class CommunicationActivity extends AppCompatActivity implements View.OnClickListener {


    Button bCall, bText, bEmail, bEmergencyCall, bEmergencyText, bEmergencyEmail;

    String etCall, etText, etEmail, etEmergencyEmail, etEmergencyCall, etEmergencyText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        ParseUser user = ParseUser.getCurrentUser();
        etCall = user.get("physicianPhone").toString();
        etText = user.get("physicianPhone").toString();
        etEmail = user.get("physicianEmail").toString();
        etEmergencyCall = user.get("emergencyContactPhone").toString();
        etEmergencyEmail = user.get("emergencyContactEmail").toString();
        etEmergencyText = user.get("emergencyContactPhone").toString();

        bText = (Button)findViewById(R.id.bText);
        bText.setOnClickListener(this);

        bEmergencyText = (Button)findViewById(R.id.bEmergencyText);
        bEmergencyText.setOnClickListener(this);

        bEmergencyCall = (Button)findViewById(R.id.bEmergencyCall);
        bEmergencyCall.setOnClickListener(this);

        bEmail = (Button)findViewById(R.id.bEmail);
        bEmail.setOnClickListener(this);

        bCall = (Button)findViewById(R.id.bCall);
        bCall.setOnClickListener(this);

        bEmergencyEmail = (Button)findViewById(R.id.bEmergencyEmail);
        bEmergencyEmail.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bEmail:

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL  , etEmail);
                email.putExtra(Intent.EXTRA_SUBJECT, "Medical Contact.");
                email.putExtra(Intent.EXTRA_TEXT   , "Dear .....");  // add name later from contacts
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,  "Choose an Email client :"));

                break;

            case R.id.bCall:

                try {
                    if (etCall != null && (etCall.length()==10
                            ||etCall.length()==11)) {
                        Uri number = Uri.parse("tel:" + etCall);
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        startActivity(callIntent);
                    }else if(etCall != null && etCall.length()==0){
                        Toast.makeText(getApplicationContext(),
                                "You missed to type the number!", Toast.LENGTH_SHORT).show();
                    }else if(etCall != null &&
                            etCall.length()<10){
                        Toast.makeText(getApplicationContext(),"Check whether you entered correct number!",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("DialerAppActivity", "error: " +
                            e.getMessage(), e);//Runtime error will be logged
                }

                break;

            case R.id.bText:

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                        + etText)));
                break;

            case R.id.bEmergencyCall:
                try {
                    if (etEmergencyCall != null && (etEmergencyCall.length()==10
                            ||etEmergencyCall.length()==11)) {
                        Uri number = Uri.parse("tel:" + etEmergencyCall);
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        startActivity(callIntent);
                    }else if(etEmergencyCall != null && etEmergencyCall.length()==0){
                        Toast.makeText(getApplicationContext(),
                                "You missed to type the number during registration!", Toast.LENGTH_SHORT).show();
                    }else if(etEmergencyCall != null &&
                            etEmergencyCall.length()<10){
                        Toast.makeText(getApplicationContext(),"Check whether you entered correct number!",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("DialerAppActivity", "error: " +
                            e.getMessage(), e);//Runtime error will be logged
                }

                break;

            case R.id.bEmergencyText:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                        + etEmergencyText)));
                break;

            case R.id.bEmergencyEmail:
                Intent eEmail = new Intent(Intent.ACTION_SEND);
                eEmail.putExtra(Intent.EXTRA_EMAIL  , etEmergencyEmail);
                eEmail.putExtra(Intent.EXTRA_SUBJECT, "Medical Contact.");
                eEmail.putExtra(Intent.EXTRA_TEXT   , "Dear .....");  // add name later from contacts
                eEmail.setType("message/rfc822");
                startActivity(Intent.createChooser(eEmail,  "Choose an Email client :"));

                break;

        }
    }
}


