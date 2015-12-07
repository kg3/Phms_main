package phms.main.Activities;

        import android.app.Activity;
        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.media.Ringtone;
        import android.media.RingtoneManager;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.SystemClock;
        import android.provider.AlarmClock;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.TimePicker;
        import android.widget.Toast;

        import com.parse.FindCallback;
        import com.parse.ParseException;
        import com.parse.ParseObject;
        import com.parse.ParseQuery;
        import com.parse.ParseUser;

        import java.util.Calendar;
        import java.util.List;

        import phms.main.R;

public class RemindersActivity extends Activity implements OnClickListener {


    DatePicker pickerDate;
    TimePicker pickerTime;
    Button readPicker;
    EditText etReminder;
    TextView info;

    TextView tList;
    final static int RQS_1 = 1;

    PendingIntent pi;
    BroadcastReceiver br;
    AlarmManager am;

    final static private long ONE_SECOND = 1000;
    final static private long TWENTY_SECONDS = ONE_SECOND * 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        info = (TextView) findViewById(R.id.info);
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

        // Setting up alarm



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
                        tList.append(reminder.get("eventsTitle").toString() + " " + reminder.get("year") + " " + reminder.get("month") + " " + reminder.get("day") + " " + reminder.get("hour") + " " + reminder.get("minute") + " " + "\n\n");
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
                reminder.put("month", pickerDate.getMonth() + 1);
                reminder.put("day", pickerDate.getDayOfMonth());
                reminder.put("hour", pickerTime.getCurrentHour());
                reminder.put("minute", pickerTime.getCurrentMinute());

                reminder.saveInBackground();

                Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
                i.putExtra(AlarmClock.EXTRA_MESSAGE, etReminder.getText().toString());
                i.putExtra(AlarmClock.EXTRA_HOUR, pickerTime.getCurrentHour());
                i.putExtra(AlarmClock.EXTRA_MINUTES, pickerTime.getCurrentMinute());
                startActivity(i);

                try {
                    reminder.save();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                setup();

                final long time = 1000 * pickerTime.getCurrentHour() + 1000*pickerTime.getCurrentMinute();

                am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + time, pi);

                loadReminders();
                break;
        }
    }

    private void setup() {

//        Intent launchClock = getPackageManager().getLaunchIntentForPackage("com.android.deskclock");
//        startActivity(launchClock);

        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent i) {
                Toast.makeText(c, etReminder.getText().toString(), Toast.LENGTH_LONG).show();
            }
        };

        final long time = 1000 * pickerTime.getCurrentHour() + 1000*pickerTime.getCurrentMinute();
        registerReceiver(br, new IntentFilter("com.authorwjf.wakeywakey") );
        pi = PendingIntent.getBroadcast( this, 0, new Intent("com.authorwjf.wakeywakey"),
                0 );
        am = (AlarmManager)(this.getSystemService( Context.ALARM_SERVICE ));

        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone ringtoneSound = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);

        //ringtoneSound.play();

    }
}

