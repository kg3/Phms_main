package phms.main.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.parse.ParseObject;
import com.parse.ParseUser;

import phms.main.R;

/**
 *
 */
public class NewNoteActivity extends AppCompatActivity {

    EditText etTitle, etNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etNote = (EditText) findViewById(R.id.etNote);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                createNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void createNote() {

        /* write to the database */
        /* anything outside of registration always needs an author */

        ParseObject note = new ParseObject("note");

        note.put("author", ParseUser.getCurrentUser());
        note.put("title", etTitle.getText().toString());
        note.put("note", etNote.getText().toString());

        note.saveInBackground();
//        //Got the current time
//        long time = System.currentTimeMillis();
//
//        //Make a new note + save
//        Note note = new Note(
//                etTitle.getText().toString(),
//                etNote.getText().toString(),
//                time
//        );
//
//        note.save();

        //Finish and let previous activity know we created
        setResult(NotePadActivity.ACTION_CREATE);
        finish();
    }
}
