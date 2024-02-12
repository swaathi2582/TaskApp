package com.example.taskapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.ui.theme.TaskAppTheme

class MainActivity : ComponentActivity(), ITaskRVAdapter {

    lateinit var viewModel : TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView1 = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView1.layoutManager = LinearLayoutManager(this)

        val adapter = TaskRVAdapter(this,this)
        recyclerView1.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TaskViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list->
            list?.let{
                adapter.updateList(it)
            }
        })

        val button1 = findViewById<Button>(R.id.button)
        button1.setOnClickListener {
            val element = findViewById<EditText>(R.id.input)
            val taskData = element.text.toString()
            if(taskData.isNotEmpty()){
                viewModel.insertTask(Tasks(taskData,'n'))
                Toast.makeText(this,"Task Inserted",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemClicked(task: Tasks) {
        viewModel.deleteTask(task)
        Toast.makeText(this,"Task Completed",Toast.LENGTH_LONG).show()
    }

}

