package com.example.todolist

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val categories = listOf(
        TaskCategory.Other,
        TaskCategory.Business,
        TaskCategory.Personal
    )

    private val tasks= mutableListOf(
        Task("pruebaBussines",TaskCategory.Business),
        Task("pruebaBussines",TaskCategory.Personal),
        Task("pruebaBussines",TaskCategory.Other )
    )
    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter


    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter

    private lateinit var fabAddTask:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
        initListeners()
        initUI()
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener{showDialog()}
    }

    private fun showDialog(){
        val dialog= Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask:Button=dialog.findViewById(R.id.btnAddTask)
        val etText:EditText=dialog.findViewById(R.id.etTask)
        val rgCategories:RadioGroup=dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener{
            val currentTask=etText.text.toString()
            if(currentTask.isNotEmpty()){
                val selectedId=rgCategories.checkedRadioButtonId
                val selectedRadioButton:RadioButton=rgCategories.findViewById(selectedId)
                val currentCategory:TaskCategory=when(selectedRadioButton.text){
                    getString(R.string.business)->TaskCategory.Business
                    getString(R.string.personal)->TaskCategory.Personal
                    else-> TaskCategory.Other

                }
                tasks.add(Task(currentTask,currentCategory))
                updateTasks()
                dialog.hide()
            }

        }


        dialog.show()
    }

    private fun initComponent() {
        rvCategories=findViewById(R.id.rvCategories)
        rvTasks=findViewById(R.id.rvTasks)
        fabAddTask=findViewById(R.id.fabAddTask)
    }

    private fun initUI() {
        categoriesAdapter= CategoriesAdapter(categories)
        rvCategories.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter=categoriesAdapter

        tasksAdapter= TasksAdapter(tasks)
        rvTasks.layoutManager=LinearLayoutManager(this)
        rvTasks.adapter=tasksAdapter
    }

    private fun updateTasks(){

        tasksAdapter.notifyDataSetChanged()
    }
}
