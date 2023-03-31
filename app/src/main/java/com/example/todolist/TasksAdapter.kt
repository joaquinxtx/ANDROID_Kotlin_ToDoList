package com.example.todolist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter (private val tasks: List<Task>) :
    RecyclerView.Adapter<TasksViewHolder>() {
    override fun getItemCount()=tasks.size

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        TODO("Not yet implemented")
    }
}