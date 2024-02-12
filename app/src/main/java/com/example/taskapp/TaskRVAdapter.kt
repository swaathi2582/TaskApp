package com.example.taskapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskRVAdapter(private val context : Context,private val listener:ITaskRVAdapter) : RecyclerView.Adapter<TaskRVAdapter.TaskViewHolder>() {

    val allNotes = ArrayList<Tasks>()

    inner class TaskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
        val completedButton = itemView.findViewById<ImageView>(R.id.completeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val viewHolder = TaskViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false))
        viewHolder.completedButton.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.task
        Log.d("TaskRVAdapter","bindviewholder")
    }

    fun updateList(newList:List<Tasks>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }

}

interface ITaskRVAdapter{
    fun onItemClicked(task:Tasks)
}