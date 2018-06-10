package android.dude.dudeproject.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Objects;

import static android.dude.dudeproject.data.Todo.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public class Todo {

    public static final String TABLE_NAME="todo_table";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "todo_id")
    public int mId;

    @ColumnInfo(name = "todo_name")
    public String mName;

    @ColumnInfo(name = "todo_date")
    public String mDate;

    public Todo(String mName, String mDate) {
        this.mName = mName;
        this.mDate = mDate;
    }

    @NonNull
    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmDate() {
        return mDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return mId == todo.mId &&
                Objects.equals(mName, todo.mName) &&
                Objects.equals(mDate, todo.mDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mId, mName, mDate);
    }
}
