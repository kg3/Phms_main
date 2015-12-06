package phms.main.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.parse.ParseUser;

import phms.main.R;

public class MenuMain extends AppCompatActivity implements View.OnClickListener {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //img = (ImageView) findViewById(R.id.img);

        //img.setImageResource(R.drawable.fitness);


        Button diet = (Button) findViewById(R.id.diet);
        Button medicine = (Button) findViewById(R.id.medicine);
        Button notes = (Button) findViewById(R.id.notes);
        Button communication = (Button) findViewById(R.id.communication);
//        Button search = (Button) findViewById(R.id.search);
        Button vital_signs = (Button) findViewById(R.id.vital_signs);
        Button reminders = (Button) findViewById(R.id.reminders);

        diet.setOnClickListener(this);
        medicine.setOnClickListener(this);
        notes.setOnClickListener(this);
        communication.setOnClickListener(this);
        //search.setOnClickListener(this);
        vital_signs.setOnClickListener(this);
        reminders.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchIntent = new Intent(getBaseContext(), SearchActivity.class);
                startActivity(searchIntent);
            }
        });
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

//            case R.id.search:
//                Intent searchIntent = new Intent(this, SearchActivity.class);
//                startActivity(searchIntent);
//                break;

            case R.id.vital_signs:
                Intent vital_signsIntent = new Intent(this, VitalsActivity.class);
                startActivity(vital_signsIntent);
                break;

            case R.id.reminders:
                Intent launchClock = getPackageManager().getLaunchIntentForPackage("com.android.deskclock");
                startActivity(launchClock);
//                Intent remindersIntent = new Intent(this, RemindersActivity.class);
//                startActivity(remindersIntent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_logout:
                ParseUser.logOut();

                Intent i = new Intent(getBaseContext(), StartActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
