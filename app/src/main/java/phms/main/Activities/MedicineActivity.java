package phms.main.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import phms.main.R;

public class MedicineActivity extends AppCompatActivity implements View.OnClickListener {

    /*
       - medication
       - potential conflicting medication // NEED TO TALK ABOUT AGAIN!
       - amount of medication
       - time/frequency to take medication

       # Monitoring
       - medication taken {yes,no}
       - medication conflicts

     */

    EditText etMedication, etAmount,etFrequency;

    Button bMonitoring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
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

        etMedication = (EditText)findViewById(R.id.etMedication);
        etAmount = (EditText)findViewById(R.id.etAmount);
        etFrequency = (EditText)findViewById(R.id.etFrequency);


        bMonitoring = (Button)findViewById(R.id.bMonitoring);
        bMonitoring.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bMonitoring:

                break;
        }
    }
}
