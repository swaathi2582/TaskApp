package com.example.taskapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//specifing all the entites , this database can have
@Database(entities= arrayOf(Tasks::class),version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    //TaskDao is accessed
    abstract fun getDao() : TaskDao


    //making it singleton as only one object has to be created
    companion object{
        //for creating singleton Class
        private var INSTANCE : TaskDatabase? = null

        fun getDatabase(context: Context):TaskDatabase {

            //if Instance is not null , return it
            //if not create a database
            //synchronised is used to make it thread safe
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_table"
                ).build()
                INSTANCE = instance

                //return instance
                instance
            }
        }
    }

}