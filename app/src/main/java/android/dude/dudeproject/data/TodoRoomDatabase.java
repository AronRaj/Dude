package android.dude.dudeproject.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 1,exportSchema = false)
public abstract class TodoRoomDatabase extends RoomDatabase{

    public abstract TodoDao todoDao();

}
