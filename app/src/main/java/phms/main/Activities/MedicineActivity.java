package phms.main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    TextView tvMedicines;


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
                Toast.makeText(getBaseContext(), "Lets add a Medicine", Toast.LENGTH_SHORT).show();
                Intent NewMedIntent = new Intent(getBaseContext(), NewMedicine.class);
                startActivity(NewMedIntent);


            }
        });


        tvMedicines = (TextView) findViewById(R.id.tvMedicines);

        loadFromParse();

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


                        tvMedicines.append("Medicine:" + _medication.get("medicationName").toString() + "\nAmount: " + _medication.get("amount").toString()
                                + "\nFrequency: " + _medication.get("frequency") + "\nConflicts: " + _medication.get("medicineConflicts") + "\n\n");
                    }
                } else {
                    tvMedicines.append("Currently No medications being taken\n");
                }
            }
        });
    }


}

