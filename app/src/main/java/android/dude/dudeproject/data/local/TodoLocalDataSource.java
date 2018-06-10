package android.dude.dudeproject.data.local;

import android.arch.lifecycle.LiveData;
import android.dude.dudeproject.data.Todo;
import android.dude.dudeproject.data.TodoDataSource;
import android.dude.dudeproject.data.TodoRoomDatabase;
import android.os.AsyncTask;

import java.util.List;

public class TodoLocalDataSource implements TodoDataSource{

    private static TodoLocalDataSource INSTANCE;

    TodoRoomDatabase todoDatabase;

    public static TodoLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TodoLocalDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private TodoLocalDataSource() {}

    public TodoLocalDataSource(TodoRoomDatabase todoDatabase) {
        this.todoDatabase = todoDatabase;
    }


    @Override
    public void addTodo(Todo todo) {
        new addAsyncTask(todoDatabase).execute(todo);
    }

    @Override
    public void deleteTodo(Todo todo) {
        new deleteAsyncTask(todoDatabase).execute(todo);
    }

    @Override
    public LiveData<List<Todo>> getAllTodo() {
        return todoDatabase.todoDao().getTodoList();
    }

    private class addAsyncTask extends AsyncTask<Todo, Void, Void> {

        private TodoRoomDatabase mAsyncTaskDao;

        addAsyncTask(TodoRoomDatabase dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Todo... params) {
            mAsyncTaskDao.todoDao().addTodo(params[0]);
            return null;
        }
    }

    private class deleteAsyncTask extends AsyncTask<Todo, Void, Void> {

        private TodoRoomDatabase mAsyncTaskDao;

        deleteAsyncTask(TodoRoomDatabase dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Todo... params) {
            mAsyncTaskDao.todoDao().deleteTodo(params[0]);
            return null;
        }
    }
}
