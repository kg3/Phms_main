package phms.main.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import phms.main.R;

public class VitalsActivity extends AppCompatActivity {

    /*
        - blood pressure
        - glucode level
        - cholesterol level
     */

    EditText etBloodPressure, etGlucose, etCholesterol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);
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

        etBloodPressure = (EditText)findViewById(R.id.etBloodPressure);
        etGlucose = (EditText)findViewById(R.id.etGlucose);
        etCholesterol = (EditText)findViewById(R.id.etCholesterol);
    }

}
