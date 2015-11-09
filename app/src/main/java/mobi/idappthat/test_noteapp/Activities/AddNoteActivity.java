package mobi.idappthat.test_noteapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

import mobi.idappthat.test_noteapp.Models.Note;
import mobi.idappthat.test_noteapp.R;

/**
 * Created by Cameron on 11/6/15.
 */
public class AddNoteActivity extends AppCompatActivity {

    EditText etTitle, etNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etNote = (EditText) findViewById(R.id.etNote);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.action_done:
                addNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addNote() {
        Calendar c = Calendar.getInstance();
        long time = System.currentTimeMillis();

        String titleString = etTitle.getText().toString();
        String noteString = etNote.getText().toString();

        Note note = new Note(titleString, noteString, time, time);
        note.save();

        setResult(MainActivity.ACTION_CREATE);
        finish();
    }
}
