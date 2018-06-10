package android.dude.dudeproject.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.dude.dudeproject.data.Todo;
import android.dude.dudeproject.data.local.TodoLocalDataSource;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    private TodoLocalDataSource mTodoLocalDataSource;

    public TodoViewModel(Application application, TodoLocalDataSource todoLocalDataSource) {
        super(application);
        this.mTodoLocalDataSource = todoLocalDataSource;
    }

    public void addTodo(Todo todo) {
        mTodoLocalDataSource.addTodo(todo);
    }

    public void deleteTodo(Todo todo) {
        mTodoLocalDataSource.deleteTodo(todo);
    }

    public LiveData<List<Todo>> getAllTodo() {
        return mTodoLocalDataSource.getAllTodo();
    }

}
