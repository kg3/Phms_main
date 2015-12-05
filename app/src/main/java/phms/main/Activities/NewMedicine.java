package phms.main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import phms.main.R;



public class NewMedicine extends AppCompatActivity {

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
        //SavingMedication.setOnClickListener(this);

        SaveAndReminder = (Button)findViewById(R.id.SaveAndReminder);
        //SaveAndReminder.setOnClickListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_note, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        setResult(NotePadActivity.ACTION_CANCEL);
        super.onBackPressed();
    }


//    @Override
//    public void onClick(View view) {
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.SavingMedication:
                createMedEntry();
                return true;

            case R.id.SaveAndReminder:
                createMedEntry();
                Intent remember = new Intent(this, RemindersActivity.class);
                startActivity(remember);

            default:
                return super.onOptionsItemSelected(item);

            //default:
        }

    }

    private void createMedEntry(){
        ParseObject MedicationEntry = new ParseObject("medications");

        MedicationEntry.put("author", ParseUser.getCurrentUser());
        MedicationEntry.put("medicationName", etMedication.getText().toString());
        MedicationEntry.put("amount", etAmount.getText().toString());
        MedicationEntry.put("frequency", etFrequency.getText().toString());
        MedicationEntry.put("medicineConflicts", etMedicationConflict.getText().toString());

        MedicationEntry.saveInBackground();

        //Finish and let previous activity know we created
        setResult(MedicineActivity.ACTION_CREATE);
        finish();
    }
}
