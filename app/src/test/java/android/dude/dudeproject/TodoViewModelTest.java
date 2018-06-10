package android.dude.dudeproject;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.res.Resources;
import android.dude.dudeproject.data.Todo;
import android.dude.dudeproject.data.local.TodoLocalDataSource;
import android.dude.dudeproject.viewmodel.TodoViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoViewModelTest {

    @Mock
    private TodoLocalDataSource mTodoLocalDataSource;

    @Mock
    private Application mContext;

    private TodoViewModel mTodoViewModel;

    private void setupContext() {
        when(mContext.getApplicationContext()).thenReturn(mContext);

        when(mContext.getResources()).thenReturn(mock(Resources.class));
    }

    @Before
    public void setupProductsViewModel() throws Exception {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        setupContext();

        // Get a reference to the class under test
        mTodoViewModel = new TodoViewModel(mContext, mTodoLocalDataSource);
    }

    @Test
    public void testInsertData(){
        Todo todo=null;
        mTodoViewModel.addTodo(todo);
    }

    @Test
    public void testDeleteData(){
        Todo todo=null;
        mTodoViewModel.deleteTodo(todo);
    }

}
