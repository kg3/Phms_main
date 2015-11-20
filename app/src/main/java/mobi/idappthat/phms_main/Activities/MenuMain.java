package mobi.idappthat.phms_main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import mobi.idappthat.phms_main.R;

public class MenuMain extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
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


        Button diet = (Button) findViewById(R.id.diet);
        Button medicine = (Button) findViewById(R.id.medicine);
        Button notes = (Button) findViewById(R.id.notes);
        Button communication = (Button) findViewById(R.id.communication);
        Button search = (Button) findViewById(R.id.search);
        Button vital_signs = (Button) findViewById(R.id.vital_signs);

        diet.setOnClickListener(this);
        medicine.setOnClickListener(this);
        notes.setOnClickListener(this);
        communication.setOnClickListener(this);
        search.setOnClickListener(this);
        vital_signs.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.diet:
                Intent dietIntent = new Intent(this, DietActivity.class);
                startActivity(dietIntent);
                break;

            case R.id.medicine:
                Intent medicineIntent = new Intent(this, MedicineActivity.class);
                startActivity(medicineIntent);
                break;
            case R.id.notes:
                Intent notesIntent = new Intent(this, NotePadActivity.class);
                startActivity(notesIntent);
                break;

            case R.id.communication:
                Intent communicationIntent = new Intent(this, CommunicationActivity.class);
                startActivity(communicationIntent);
                break;
            case R.id.search:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                break;

            case R.id.vital_signs:
                Intent vital_signsIntent = new Intent(this, MedicineActivity.class);
                startActivity(vital_signsIntent);
                break;
        }
    }
}
