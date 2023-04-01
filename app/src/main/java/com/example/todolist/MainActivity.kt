package com.example.todolist

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.function.Predicate

class MainActivity : AppCompatActivity() {

    private val categories = listOf(
        TaskCategory.Other,
        TaskCategory.Business,
        TaskCategory.Personal
    )

    private val tasks= mutableListOf(

        Task("Personal",TaskCategory.Personal),
        Task("Business",TaskCategory.Business),
        Task("Other",TaskCategory.Other ),
    )
    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter


    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter


    private lateinit var fabAddTask:FloatingActionButton
    private lateinit var fabDeleteTasks:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
        initListeners()
        initUI()
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener{showDialog()}

        fabDeleteTasks.setOnClickListener{deleteData()}
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
    private fun deleteData() {
            tasks.removeAll{it.isSelected}
            updateTasks()
    }

    private fun initComponent() {
        rvCategories=findViewById(R.id.rvCategories)
        rvTasks=findViewById(R.id.rvTasks)
        fabAddTask=findViewById(R.id.fabAddTask)

        fabDeleteTasks=findViewById(R.id.fabDeleteTasks)
    }

    private fun initUI() {
        categoriesAdapter= CategoriesAdapter(categories){updateCategories(it)}
        rvCategories.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter=categoriesAdapter

        tasksAdapter= TasksAdapter(tasks){onItemSelected(it)}
        rvTasks.layoutManager=LinearLayoutManager(this)
        rvTasks.adapter=tasksAdapter
    }

    private fun  onItemSelected(position:Int){
        tasks[position].isSelected=!tasks[position].isSelected
        updateTasks()
    }

    private fun updateCategories(position:Int){
        categories[position].isSelected=!categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }
    private fun updateTasks(){
        val selectedCategories:List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter{selectedCategories.contains(it.category)}
        tasksAdapter.tasks=newTasks
        tasksAdapter.notifyDataSetChanged()
    }
}
