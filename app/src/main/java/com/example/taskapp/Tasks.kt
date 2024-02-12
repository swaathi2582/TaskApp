package com.example.taskapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//to convert a class into entity , annotate it as @Entity and provide a name
//Entity is a DataBase table
@Entity(tableName = "task_table")

//provide column names inside constructor
class Tasks (@ColumnInfo(name="tasks")var task : String,
    @ColumnInfo(name="completion_info")var ci : Char ){
    //as id is not given as input it is not given inside constructor
    @PrimaryKey(autoGenerate = true) var id = 0
}