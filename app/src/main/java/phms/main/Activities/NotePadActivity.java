package phms.main.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import phms.main.Models.NoteAdapter;
import phms.main.R;


/**
 * https://parse.com/docs/android/guide#objects-deleting-objects
 * http://www.vogella.com/tutorials/AndroidListView/article.html
 * https://github.com/ParsePlatform/ParseQueryAdapterTutorial/blob/master/src/com/parse/samples/parsequeryadapter/MainActivity.java
 */
public class NotePadActivity extends AppCompatActivity {

    public static final int CODE_NEW_NOTE = 0;
    public static final int ACTION_CANCEL = 0;
    public static final int ACTION_CREATE = 1;
    /* Declare Android Layouts */
    TextView tvNotes;
    /* !! PARSE !! */
    private ParseQueryAdapter<ParseObject> mainAdapter;
    private NoteAdapter noteAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Instantiate Android Layouts */
        //tvNotes = (TextView) findViewById(R.id.tvNotes);          /* !! PARSE !! */

        /* load from database */
        //loadFromParse();

        // /* !! PARSE !! */ Initialize main ParseQueryAdapter
        mainAdapter = new ParseQueryAdapter<ParseObject>(this, "note");
        mainAdapter.setTextKey("bothTitleAndNote");
        //mainAdapter.setTextKey("note");
        //mainAdapter.setImageKey("image");


        noteAdapter = new NoteAdapter(this);
        //noteAdapter.setTextKey("title");

        // /* !! PARSE !! */ Initialize ListView and set initial view to mainAdapter
        listView = (ListView) findViewById(R.id.list);

        /* noteAdapter */
//        listView.setAdapter(noteAdapter);
//        noteAdapter.loadObjects();
//        noteAdapter.setAutoload(true);

        /* mainAdapter */
        listView.setAdapter(mainAdapter);
        mainAdapter.loadObjects();
        mainAdapter.setAutoload(true);


        /* Button for Making a Note */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNote = new Intent(getBaseContext(), NewNoteActivity.class);
                startActivityForResult(addNote, CODE_NEW_NOTE);
            }
        });

        /* float_button to toggle different views */
//        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
//        fab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /* attempting to load custom adapter in view */
//                if (listView.getAdapter() == mainAdapter) {
//                    listView.setAdapter(noteAdapter);
//                    noteAdapter.loadObjects();
//
//                } else {
//                    listView.setAdapter(mainAdapter);
//                    mainAdapter.loadObjects();
//
//                }
//
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                ParseObject temp = (ParseObject) listView.getAdapter().getItem(position);
                Toast.makeText(getApplicationContext(),
                        "  " + temp.get("note"), Toast.LENGTH_LONG).show();


            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                ParseObject temp = (ParseObject) listView.getAdapter().getItem(position);

                Toast.makeText(getApplicationContext(),
                        "Deleting " + temp.get("note"), Toast.LENGTH_LONG).show();


                temp.deleteInBackground();

                mainAdapter.loadObjects();

                view.setSelected(true);

                return true;
            }
        });


        //        // /* !! PARSE !! */ Initialize toggle button
//        Button toggleButton = (Button) findViewById(R.id.toggleButton);
//        toggleButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listView.getAdapter() == noteAdapter) {     // switching from main as main to note as the first view
//                    listView.setAdapter(mainAdapter);
//                    mainAdapter.loadObjects();
//                    mainAdapter.setAutoload(true);
//                } else {
//                    listView.setAdapter(noteAdapter);
//                    noteAdapter.loadObjects();
//                    noteAdapter.setAutoload(true);
//                }
//            }
//
//        });

    }


//    private void loadFromParse() {
//
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("note");
//        query.whereEqualTo("author", ParseUser.getCurrentUser());
//
//        /* update your code with this line */
//        query.orderByDescending("createdAt");
//
//        query.findInBackground(new FindCallback<ParseObject>() {
//            public void done(List<ParseObject> allNotes, ParseException e) {
//
//                if (!allNotes.isEmpty()) {
//                    tvNotes.setText("");
//                    for (ParseObject _note : allNotes) {
//
//
//                        tvNotes.append("* " + _note.get("title").toString() + " # " + _note.get("note").toString() + "\n\n");
//                    }
//                }
//            }
//        });
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_NEW_NOTE) {

            //Note was created! update it!
            if (resultCode == ACTION_CREATE) {
                Toast.makeText(getBaseContext(), "Note Added", Toast.LENGTH_SHORT).show();
                //loadFromParse();
                //noteAdapter.loadObjects();
                mainAdapter.loadObjects();
            }

            //Note was canceled
            else if (resultCode == ACTION_CANCEL) {
                Toast.makeText(getBaseContext(), "Not saved, no new note", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
