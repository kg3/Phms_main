package mobi.idappthat.test_noteapp.Models;

import com.orm.SugarRecord;

/**
 * Created by Cameron on 11/6/15.
 */
public class Note extends SugarRecord<Note> {

    String title, contents;
    long createdAt, updatedAt;
    boolean isDirty;
    boolean isDeleted;

    public Note() {}

    public Note(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Note(String title, String contents, long createdAt, long updatedAt) {
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
