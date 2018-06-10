package android.dude.dudeproject.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.dude.dudeproject.R;
import android.dude.dudeproject.data.Todo;
import android.dude.dudeproject.util.ViewModelFactory;
import android.dude.dudeproject.viewmodel.TodoViewModel;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TodoAdapter mTodoAdapter;
    private TodoViewModel mTodoViewModel;
    private List<Todo> mTodoList;
    private RecyclerView todosRecyclerView;
    private Button addTodoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todosRecyclerView = findViewById(R.id.todo_recycler_view);
        addTodoButton = findViewById(R.id.add_todo_button);
        addTodoButton.setOnClickListener(this);
        initializeViewModel();
        setupRecyclerView();
        listenForTodoAddition();
    }

    private void initializeViewModel() {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());

        mTodoViewModel = ViewModelProviders.of(this, factory).get(TodoViewModel.class);
    }

    private void setupRecyclerView() {
        mTodoAdapter = new TodoAdapter(mTodoViewModel,new ArrayList<Todo>());
        todosRecyclerView.setAdapter(mTodoAdapter);
    }

    private void listenForTodoAddition() {
        mTodoViewModel.getAllTodo().observe(this, new Observer<List<Todo>>() {
            @Override public void onChanged(@Nullable List<Todo> todoList) {
                if (todoList != null && !todoList.isEmpty()) {
                    mTodoAdapter.addAllTodo(todoList);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_todo_button: {
                addToDo();
                break;
            }
        }
    }

    private void addToDo() {
        final EditText nameEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new todo")
                .setMessage("What do you want to do next?")
                .setView(nameEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = String.valueOf(nameEditText.getText());
                        long date = System.currentTimeMillis();
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = formatter.format(date);
                        Todo todo = new Todo(name, strDate);
                        mTodoViewModel.addTodo(todo);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
}
