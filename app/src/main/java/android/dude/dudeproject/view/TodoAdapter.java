package android.dude.dudeproject.view;

import android.dude.dudeproject.R;
import android.dude.dudeproject.data.Todo;
import android.dude.dudeproject.viewmodel.TodoViewModel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import java.util.Date;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<Todo> mTodoList;
    private TodoViewModel mTodoViewModel;

    public TodoAdapter(TodoViewModel todoViewModel,List<Todo> todoList) {
        mTodoViewModel=todoViewModel;
        mTodoList = todoList;
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        Todo todo = mTodoList.get(position);
        holder.getNameTextView().setText(todo.getmName());
        holder.getDateTextView().setText((new Date(todo.getmDate()).toString()));
    }

    @Override
    public int getItemCount() {
        return mTodoList.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvName;
        private final TextView mTvDate;

        TodoViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvDate = itemView.findViewById(R.id.tvDate);
            Button btnDelete = itemView.findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Todo todo = mTodoList.get(pos);
                    mTodoViewModel.deleteTodo(todo);
                }
            });
        }

        TextView getNameTextView() {
            return mTvName;
        }

        TextView getDateTextView() {
            return mTvDate;
        }

    }

    void addAllTodo(List<Todo> todoList) {

        this.mTodoList = todoList;

        notifyDataSetChanged();
    }
}
