package phms.main.Activities;

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

public class DietActivity extends AppCompatActivity implements View.OnClickListener {

    /*
        - date
        - calorie count
        - weight
     */

    EditText etCalorieCount, etFoodIntake, etWeight;
    Button bDiet;
    TextView etTotal;
    TextView tList;

    Integer sum=0;

    // ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        // img = (ImageView) findViewById(R.id.img);

        // img.setImageResource(R.drawable.diet);

        etFoodIntake = (EditText)findViewById(R.id.etFoodIntake);
        etCalorieCount = (EditText)findViewById(R.id.etCalorieCount);
        etWeight = (EditText)findViewById(R.id.etWeight);

        etTotal=(TextView)findViewById(R.id.etTotal);
        tList = (TextView)findViewById(R.id.tList);

        bDiet = (Button) findViewById(R.id.bDiet);
        bDiet.setOnClickListener(this);

        loadDiet();
    }

    public void loadDiet() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("diet");
        query.whereEqualTo("author", ParseUser.getCurrentUser());

        query.orderByDescending("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> allDiet, ParseException e) {


                if (allDiet.size() > 0) {
                    tList.setText("");
                    for (ParseObject diet : allDiet) {
                        sum = sum + Integer.parseInt(diet.get("calorieCount").toString());
                        tList.append("Food Intake: "+diet.get("foodIntake").toString() + "\nCalorie Count:" + " "
                                + diet.get("calorieCount").toString()
                                + " " + "\nWeight: " + diet.get("weight").toString() + " " +"\n\n");
                    }
                    etTotal.setText("Total: " + Integer.toString(sum));
                    sum=0;
                }

            }

        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bDiet:

                ParseObject diet = new ParseObject("diet");

                diet.put("author", ParseUser.getCurrentUser());
                diet.put("foodIntake", etFoodIntake.getText().toString());
                diet.put("calorieCount", etCalorieCount.getText().toString());
                diet.put("weight", etWeight.getText().toString());


                try {
                    diet.save();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //diet.saveInBackground();

                loadDiet();

                break;
        }
    }


}
