package com.example.taskapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

//Data Access Object
//DAO acts as an interface between the Database and other part of the application
//it gets data from the database and modifies it
//all the operations that are done on the data is done through DAO


//To convert an interface into DAO, annotate it as DAO
@Dao
interface TaskDao {

    //suspend is used to make the function run in the background
    //insert and delete are io operation , if it runs in the main thread , it will take a lot of time
    //and make the app slower, thus it is made to run in the background

    //insert - inserts the input into the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task:Tasks)

    //delete - deletes the selected data from the database
    @Delete
    suspend fun delete(task: Tasks)

    //LiveData -> LifeCycle aware..Updates data in the ui whenever there is a change data
    //observes data and notifies Observers
    //used in conjuction with ViewModel to manage ui related data eg.surviving configuration changes

    //displays all the data in the table
    @Query("SELECT * FROM task_table")
    fun displayAllTasks() : LiveData<List<Tasks>>


}