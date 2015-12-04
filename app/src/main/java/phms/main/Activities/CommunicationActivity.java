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



import phms.main.R;

public class CommunicationActivity extends AppCompatActivity implements View.OnClickListener {

    /*
        - SMS
        - Email
        - Phone Number
     */

    EditText etEmail, etCall;
    Button bCall, bText, bEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        etEmail = (EditText)findViewById(R.id.etEmail);
        etCall = (EditText)findViewById(R.id.etCall);

        bText = (Button)findViewById(R.id.bText);
        bText.setOnClickListener(this);

        bEmail = (Button)findViewById(R.id.bEmail);
        bEmail.setOnClickListener(this);

        bCall = (Button)findViewById(R.id.bCall);
        bCall.setOnClickListener(this);

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bEmail:

                break;

            case R.id.bCall:

                try {
                    if (etCall != null && (etCall.getText().length()==10
                            ||etCall.getText().length()==11)) {
                        Uri number = Uri.parse("tel:" + etCall.getText());
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        startActivity(callIntent);
                    }else if(etCall != null && etCall.getText().length()==0){
                        Toast.makeText(getApplicationContext(),
                                "You missed to type the number!", Toast.LENGTH_SHORT).show();
                    }else if(etCall != null &&
                            etCall.getText().length()<10){
                        Toast.makeText(getApplicationContext(),"Check whether you entered correct number!",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("DialerAppActivity", "error: " +
                            e.getMessage(), e);//Runtime error will be logged
                }

                break;

            case R.id.bText:

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                        + etCall.getText().toString())));
                break;
        }
    }
}


