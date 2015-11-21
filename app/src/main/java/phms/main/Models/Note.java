package phms.main.Models;

import com.orm.SugarRecord;

/**
 * nlm (- -) mln
 */
public class Note extends SugarRecord<Note> {

    String title, note;
    long createdAt;

    public Note() {
    }

    public Note(String title, String note, long createdAt) {
        this.title = title;
        this.note = note;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
