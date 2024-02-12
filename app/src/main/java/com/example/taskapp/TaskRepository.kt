package com.example.taskapp

import android.util.Log
import androidx.lifecycle.LiveData

//helps to get data from various data sources like database,api,other apps etc
//viewModel interacts with repository instead of interacting with all the data sources
class TaskRepository(private val taskdao:TaskDao) {

    val allNotes : LiveData<List<Tasks>> = taskdao.displayAllTasks()

    //data is inserted into the database via repository ..not directly into the database
    //repository then connects to dao and performs the actions
    //insertion implementation is taken care by dao
    suspend fun insert(task:Tasks){
        Log.d("TaskRepository","Repository called")
        taskdao.insert(task)
    }

    suspend fun delete(task:Tasks){
        taskdao.delete(task)
    }

}