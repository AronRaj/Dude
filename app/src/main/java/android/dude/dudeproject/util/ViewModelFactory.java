package android.dude.dudeproject.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.dude.dudeproject.data.TodoRoomDatabase;
import android.dude.dudeproject.data.local.TodoLocalDataSource;
import android.dude.dudeproject.viewmodel.TodoViewModel;
import android.support.annotation.VisibleForTesting;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    @SuppressLint("StaticFieldLeak") private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private final TodoRoomDatabase mTodoDatabase;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application, Injection.provideTodoDatabase(application));
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application, TodoRoomDatabase database) {

        mApplication = application;

        mTodoDatabase = database;
    }

    @Override public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(TodoViewModel.class)) {

            //noinspection unchecked
            return (T) new TodoViewModel(mApplication,  new TodoLocalDataSource(mTodoDatabase));
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
