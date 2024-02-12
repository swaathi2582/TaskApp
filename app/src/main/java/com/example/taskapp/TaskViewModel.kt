package com.example.taskapp

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    val repository : TaskRepository
    val allNotes : LiveData<List<Tasks>>

    init{
        val dao = TaskDatabase.getDatabase(application).getDao()
        repository = TaskRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteTask(task:Tasks) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(task)
    }

    fun insertTask(task:Tasks) = viewModelScope.launch(Dispatchers.IO){
        Log.d("TaskViewModel","viewModel called")
        repository.insert(task)
    }

}