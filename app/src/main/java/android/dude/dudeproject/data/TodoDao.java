package android.dude.dudeproject.data;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTodo(Todo todo);

    @Query("Select * from " + Todo.TABLE_NAME)
    LiveData<List<Todo>> getTodoList();

    @Delete
    void deleteTodo(Todo todo);

    @Query("Delete from " + Todo.TABLE_NAME)
    void deleteAllTodo();

}
