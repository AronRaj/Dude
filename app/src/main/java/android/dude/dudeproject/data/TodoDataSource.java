package android.dude.dudeproject.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface TodoDataSource {

    void addTodo(Todo todo);

    void deleteTodo(Todo todo);

    LiveData<List<Todo>> getAllTodo();
}
