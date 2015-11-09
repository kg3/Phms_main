package mobi.idappthat.test_noteapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobi.idappthat.test_noteapp.Activities.AddNoteActivity;
import mobi.idappthat.test_noteapp.Activities.MainActivity;
import mobi.idappthat.test_noteapp.Adapters.ListAdapter;
import mobi.idappthat.test_noteapp.Models.Note;
import mobi.idappthat.test_noteapp.R;

/**
 * Created by Cameron on 11/6/15.
 */
public class NotesFragment extends Fragment {

    RecyclerView list;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter listAdapter;

    List<Note> notes;

    public NotesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        notes = Note.listAll(Note.class);

        RecyclerView list = (RecyclerView) view.findViewById(R.id.rvMain);
        list.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        list.setLayoutManager(layoutManager);

        listAdapter = new ListAdapter(notes);
        list.setAdapter(listAdapter);

        FloatingActionButton fab = ((MainActivity)getActivity()).getFab();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), AddNoteActivity.class);
                startActivityForResult(i, MainActivity.CODE_NOTES);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Notes request
        if(requestCode == MainActivity.CODE_NOTES) {
            if(resultCode == MainActivity.ACTION_CREATE) {
                Toast.makeText(getActivity(), "New note", Toast.LENGTH_SHORT).show();
                //test
            }
        }
    }
}
