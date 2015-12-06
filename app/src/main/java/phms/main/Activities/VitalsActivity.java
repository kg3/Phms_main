package phms.main.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import phms.main.R;

public class VitalsActivity extends AppCompatActivity implements View.OnClickListener {

    /*
        - blood pressure
        - glucode level
        - cholesterol level
     */

    EditText etBloodPressure, etGlucose, etCholesterol;
    Button bVital;
    TextView tList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitals);
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

        etBloodPressure = (EditText)findViewById(R.id.etBloodPressure);
        etGlucose = (EditText)findViewById(R.id.etGlucose);
        etCholesterol = (EditText)findViewById(R.id.etCholesterol);

        tList = (TextView) findViewById(R.id.tList);
        loadVitals();

        bVital = (Button) findViewById(R.id.bVital);
        bVital.setOnClickListener(this);
    }

    public void loadVitals() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("vitals");
        query.whereEqualTo("author", ParseUser.getCurrentUser());

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> allVitals, ParseException e) {



                if (allVitals.size() > 0) {
                    tList.setText("");
                    for (ParseObject vitals : allVitals) {
                        tList.append("Blood Pressure: "+vitals.get("bloodPressure").toString() + "\nGlucose:" + " " + vitals.get("glucose").toString() + " " + "\nCholesterol: " +vitals.get("cholesterol").toString() + " " +"\n\n");
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bVital:

                ParseObject vitals = new ParseObject("vitals");

                vitals.put("author", ParseUser.getCurrentUser());
                vitals.put("bloodPressure", etBloodPressure.getText().toString());
                vitals.put("glucose", etGlucose.getText().toString());
                vitals.put("cholesterol", etCholesterol.getText().toString());


                vitals.saveInBackground();

                loadVitals();

                break;
        }
    }

}
