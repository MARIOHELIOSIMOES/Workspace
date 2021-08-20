package simoes.mario.todo.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import simoes.mario.todo.databinding.ActivityMainBinding
import simoes.mario.todo.datasource.TaskDataSoucer
import simoes.mario.todo.datasource.TaskDataSoucer.getList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {TaskListAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvLista.adapter = adapter
        insertListeners()
        updateList()
    }

    private fun insertListeners() {
        binding.fabNewTask.setOnClickListener {
            startActivityForResult(Intent(this, AddTaskActivity::class.java), CREATE_NEW_TASK)
        }
        adapter.listenerDelete = {
            TaskDataSoucer.deleteTask(it)
        }
        adapter.listenerEdit = {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivityForResult(intent, CREATE_NEW_TASK)
        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) {
            updateList()
        }
    }
    private fun updateList(){
        adapter.submitList(TaskDataSoucer.getList())
    }

    companion object {
        private const val CREATE_NEW_TASK = 1000
    }
}