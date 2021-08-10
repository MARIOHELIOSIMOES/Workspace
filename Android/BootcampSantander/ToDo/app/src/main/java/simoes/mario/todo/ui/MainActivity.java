package simoes.mario.todo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import simoes.mario.todo.databinding.ActivityMainBinding;
import simoes.mario.todo.ui.AddTaskActivity;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TaskListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        listAdapter = new TaskListAdapter();
        binding.rvLista.setAdapter(listAdapter);

        insertListeners();

    }

    private void insertListeners() {
        binding.fabNewTask.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddTaskActivity.class);
            startActivity(intent);
                }
        );
    }

    /*continuar no video 'mostrando a lista de tarefas'*/
}