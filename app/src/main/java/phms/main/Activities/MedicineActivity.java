package phms.main.Activities;

import android.content.Intent;
import android.os.Bundle;
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

public class MedicineActivity extends AppCompatActivity implements View.OnClickListener {


    EditText etMedication, etAmount, etFrequency, etMedicationConflict;
    Button SavingMedication;
    Button SaveAndReminder;
    TextView tvMedicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
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
        etMedication = (EditText) findViewById(R.id.etMedication);
        etAmount = (EditText) findViewById(R.id.etAmount);
        etFrequency = (EditText) findViewById(R.id.etFrequency);
        etMedicationConflict = (EditText) findViewById(R.id.etMedicationConflict);

        SavingMedication = (Button) findViewById(R.id.SavingMedication);
        SaveAndReminder = (Button) findViewById(R.id.SaveAndReminder);

        tvMedicines = (TextView) findViewById(R.id.tvMedicines);

        loadFromParse();

        SaveAndReminder.setOnClickListener(this);
        SavingMedication.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.SavingMedication:

                saveToParse();

                loadFromParse();

                break;
            case R.id.SaveAndReminder:
                saveToParse();

                Intent remindersIntent = new Intent(this, RemindersActivity.class);
                startActivity(remindersIntent);

                break;

        }
    }

    private void saveToParse() {
        ParseObject MedicationEntry = new ParseObject("medications");

        MedicationEntry.put("author", ParseUser.getCurrentUser());
        MedicationEntry.put("medicationName", etMedication.getText().toString());
        MedicationEntry.put("amount", etAmount.getText().toString());
        MedicationEntry.put("frequency", etFrequency.getText().toString());
        MedicationEntry.put("medicineConflicts", etMedicationConflict.getText().toString());

        MedicationEntry.saveInBackground();
    }


    private void loadFromParse() {

        /* query the database */

        ParseQuery<ParseObject> query = ParseQuery.getQuery("medications");
        query.whereEqualTo("author", ParseUser.getCurrentUser());

        /* update your code with this line */
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> allMedication, ParseException e) {
                // commentList now has the comments for myPost

                if (allMedication.size() > 0) {
                    tvMedicines.setText("");
                    for (ParseObject _medication : allMedication) {


                        tvMedicines.append("* " + _medication.get("medicationName").toString() + " # " + _medication.get("amount").toString()
                                + " - " + _medication.get("frequency") + " - " + _medication.get("medicationConflicts") + "\n\n");
                    }
                }
            }
        });
    }

}

