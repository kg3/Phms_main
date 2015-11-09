package mobi.idappthat.test_noteapp.Adapters;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mobi.idappthat.test_noteapp.Models.Note;
import mobi.idappthat.test_noteapp.R;

/**
 * Created by Cameron on 11/6/15.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    ViewHolder viewHolder;
    List<Note> notes;

    public ListAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_note_list_item, parent, false);

        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        holder.setup(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivIcon;
        TextView tvTitle, tvDate;

        public ViewHolder(View itemView) {
            super(itemView);

            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);


            itemView.setOnClickListener(this);
        }

        public void setup(Note note) {
            tvTitle.setText(note.getTitle());
        }

        @Override
        public void onClick(View v) {
            Snackbar.make(v, "Click" + getLayoutPosition(), Snackbar.LENGTH_SHORT).show();
        }
    }
}
