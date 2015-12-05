package phms.main.Models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import phms.main.R;

public class NoteAdapter extends ParseQueryAdapter<ParseObject> {

    public NoteAdapter(Context context) {
        // Use the QueryFactory to construct a PQA that will only show
        // notes for author; createdAt descending
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("notes");
                query.whereEqualTo("author", ParseUser.getCurrentUser());
                query.orderByDescending("createdAt");
                return query;
            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.custom_list_item, null);
        }

        super.getItemView(object, v, parent);

//        // Add and download the image
//        ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.icon);
//        ParseFile imageFile = object.getParseFile("image");
//        if (imageFile != null) {
//            todoImage.setParseFile(imageFile);
//            todoImage.loadInBackground();
//        }

        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.text1);
        titleTextView.setText(object.getString("title"));

        TextView noteTextView = (TextView) v.findViewById(R.id.text2);
        noteTextView.setText(object.getString("note"));

        // Add a reminder of how long this item has been outstanding
        TextView timestampView = (TextView) v.findViewById(R.id.timestamp);
        timestampView.setText(object.getCreatedAt().toString());
        return v;
    }

}