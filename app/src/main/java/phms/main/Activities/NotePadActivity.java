package phms.main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import phms.main.Models.Note;
import phms.main.R;

/**
 *
 */
public class NotePadActivity extends AppCompatActivity {

    public static final int CODE_NEW_NOTE = 0;
    public static final int CODE_NEW_TODO = 1;

    public static final int ACTION_CANCEL = 0;
    public static final int ACTION_CREATE = 1;

    public static final String FLASH_WELCOME = "flash_welcome";


    TextView tvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Inflate widget and update with notes
        tvNotes = (TextView) findViewById(R.id.tvNotes);
        updateNotes();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNote = new Intent(getBaseContext(), NewNoteActivity.class);
                startActivityForResult(addNote, CODE_NEW_NOTE);
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

    private void updateNotes() {
        List<Note> notes = Note.listAll(Note.class);

        //Make sure thee are actually notes!
        if (notes.size() > 0) {
            tvNotes.setText("");
            for (Note n : notes) {
                tvNotes.append("- " + n.getTitle() + " - " + n.getNote() + "\n\n");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_NEW_NOTE) {

            //Note was created! update it!
            if (resultCode == ACTION_CREATE) {
                Toast.makeText(getBaseContext(), "Cool yo!", Toast.LENGTH_SHORT).show();
                updateNotes();
            }

            //Note was canceled
            else if (resultCode == ACTION_CANCEL) {
                Toast.makeText(getBaseContext(), "Sad day :(", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
