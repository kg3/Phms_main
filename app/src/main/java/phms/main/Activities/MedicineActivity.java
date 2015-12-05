package phms.main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import phms.main.R;

public class MedicineActivity extends AppCompatActivity {
    public static final int CODE_NEW_NOTE = 0;
    public static final int CODE_NEW_TODO = 1;

    public static final int ACTION_CANCEL = 0;
    public static final int ACTION_CREATE = 1;

    public static final String FLASH_WELCOME = "flash_welcome";

    TextView tvMedicines;
    ListView lvMedicinesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Inflate widget and update with notes
        tvMedicines = (TextView) findViewById(R.id.tvMedications);

        //lvMedicinesList = (ListView) findViewById(R.id.);
        //updateNotes();

        loadFromParse();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent NewMedicine = new Intent(getBaseContext(), NewMedicine.class);
                startActivityForResult(NewMedicine, CODE_NEW_NOTE);
            }
        });

        //Welcome message if needed
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getBoolean(FLASH_WELCOME, false)) {
                View v = findViewById(R.id.root_view);

                Snackbar.make(v, "Welcome!", Snackbar.LENGTH_LONG)
                        .setAction("Tutorial", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //nothing
                            }
                        }).show();

            }
        }
    }


    private void loadFromParse() {

        /* query the database */

        ParseQuery<ParseObject> query = ParseQuery.getQuery("medication");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_NEW_NOTE) {

            //Note was created! update it!
            if (resultCode == ACTION_CREATE) {
                Toast.makeText(getBaseContext(), "Cool yo!", Toast.LENGTH_SHORT).show();
                loadFromParse();
            }

            //Note was canceled
            else if (resultCode == ACTION_CANCEL) {
                Toast.makeText(getBaseContext(), "Sad day :(", Toast.LENGTH_SHORT).show();
            }
        }
    }


}

