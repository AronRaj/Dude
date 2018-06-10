package android.dude.dudeproject.util;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.dude.dudeproject.data.TodoRoomDatabase;


/**
 * Created by Aron on 10/06/18.
 */

public class Injection {


  public static TodoRoomDatabase provideTodoDatabase(Context context) {
    return Room.databaseBuilder(context, TodoRoomDatabase.class, "todo_db").build();
  }
}
