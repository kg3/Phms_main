package phms.main.Activities;

import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import phms.main.R;

public class RemindersActivity extends Activity implements OnClickListener {


    DatePicker pickerDate;
    TimePicker pickerTime;
    Button readPicker;
    EditText etReminder;

    TextView tList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        etReminder = (EditText) findViewById(R.id.etReminder);
        tList = (TextView) findViewById(R.id.tList);
        loadReminders();

        pickerDate = (DatePicker) findViewById(R.id.pickerdate);
        pickerTime = (TimePicker) findViewById(R.id.pickertime);

        Calendar now = Calendar.getInstance();

        pickerDate.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);

        pickerTime.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        pickerTime.setCurrentMinute(now.get(Calendar.MINUTE));


        now.set(pickerDate.getYear(),
                pickerDate.getMonth(),
                pickerDate.getDayOfMonth(),
                pickerTime.getCurrentHour(),
                pickerTime.getCurrentMinute(),
                00);

        readPicker = (Button) findViewById(R.id.readpicker);
        readPicker.setOnClickListener(this);

    }

    public void loadReminders() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("reminder");
        query.whereEqualTo("author", ParseUser.getCurrentUser());

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> allReminders, ParseException e) {


                if (allReminders.size() > 0) {
                    tList.setText("");
                    for (ParseObject reminder : allReminders) {
                        tList.append(reminder.get("eventsTitle").toString() + " " + reminder.get("year") + " " + reminder.get("month")+" " + reminder.get("day")+ " "+ reminder.get("hour")+" "+ reminder.get("minute") + " " + "\n\n");
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.readpicker:

                ParseObject reminder = new ParseObject("reminder");

                reminder.put("author", ParseUser.getCurrentUser());
                reminder.put("eventsTitle", etReminder.getText().toString());
                reminder.put("year", pickerDate.getYear());
                reminder.put("month", pickerDate.getMonth()+1);
                reminder.put("day", pickerDate.getDayOfMonth());
                reminder.put("hour", pickerTime.getCurrentHour());
                reminder.put("minute", pickerTime.getCurrentMinute());


                reminder.saveInBackground();

                loadReminders();

                break;
        }
    }
}