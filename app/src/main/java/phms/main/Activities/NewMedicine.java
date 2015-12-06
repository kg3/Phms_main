package phms.main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;

import phms.main.R;



public class NewMedicine extends AppCompatActivity implements View.OnClickListener{


    /*
       - medication
       - potential conflicting medication // NEED TO TALK ABOUT AGAIN!
       - amount of medication
       - time/frequency to take medication

       # Monitoring
       - medication taken {yes,no}
       - medication conflicts

     */

    EditText etMedication, etAmount,etFrequency,etMedicationConflict;
    Button SavingMedication;
    Button SaveAndReminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medicine);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        etMedication = (EditText)findViewById(R.id.etMedication);
        etAmount = (EditText)findViewById(R.id.etAmount);
        etFrequency = (EditText)findViewById(R.id.etFrequency);
        etMedicationConflict = (EditText)findViewById(R.id.etMedicationConflict);

        SavingMedication = (Button)findViewById(R.id.SavingMedication);
        SavingMedication.setOnClickListener(this);

        SaveAndReminder = (Button)findViewById(R.id.SaveAndReminder);
        SaveAndReminder.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.SavingMedication:
                saveToParse();
                Toast.makeText(getBaseContext(), "Medication Saved", Toast.LENGTH_SHORT).show();
                Intent menu = new Intent(this, MenuMain.class);
                startActivity(menu);
                return;

            case R.id.SaveAndReminder:
                Toast.makeText(getBaseContext(), "Lets create a reminder", Toast.LENGTH_SHORT).show();
                saveToParse();
                Intent reminder = new Intent(this, RemindersActivity.class);
                startActivity(reminder);

        }

    }


    private void saveToParse(){
        ParseObject MedicationEntry = new ParseObject("medications");

        MedicationEntry.put("author", ParseUser.getCurrentUser());
        MedicationEntry.put("medicationName", etMedication.getText().toString());
        MedicationEntry.put("amount", etAmount.getText().toString());
        MedicationEntry.put("frequency", etFrequency.getText().toString());
        MedicationEntry.put("medicineConflicts", etMedicationConflict.getText().toString());

        MedicationEntry.saveInBackground();

        finish();
    }
}
