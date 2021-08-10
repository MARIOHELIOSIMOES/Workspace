package simoes.mario.todo.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import simoes.mario.todo.databinding.ActivityAddTaskBinding
import simoes.mario.todo.datasource.TaskDataSoucer
import simoes.mario.todo.extensions.format
import simoes.mario.todo.extensions.text
import simoes.mario.todo.model.Task
import java.util.*

class AddTaskActivity : AppCompatActivity(){

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        insertListener()
    }

    private fun insertListener() {
        binding.tilData.editText?.setOnClickListener{
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select date")
                    .build()

            datePicker.addOnPositiveButtonClickListener {
                        val timeZone = TimeZone.getDefault()
                        val offset = timeZone.getOffset((Date().time) * -1)
                        binding.tilData.text = Date(it+offset).format()
                    }

            datePicker.show(supportFragmentManager, "TAG_DATA")
        }

        binding.tilHora.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .build()
            timePicker.addOnPositiveButtonClickListener{
                binding.tilHora.text = "${timePicker.hour} : ${timePicker.minute}"
            }
            timePicker.show(supportFragmentManager, null)
        }

        binding.btnCriar.setOnClickListener{
            val task = Task(title = binding.tilTitulo.text,
            hour = binding.tilHora.text,
            date = binding.tilData.text)
            TaskDataSoucer.insertTask(task)
            Log.e("TAG", "insertListener " + TaskDataSoucer.getList())
        }
        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }
}