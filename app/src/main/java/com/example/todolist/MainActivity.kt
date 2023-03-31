package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val categories = listOf(
        TaskCategory.Other,
        TaskCategory.Business,
        TaskCategory.Personal
    )
    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter


    private lateinit var rvTasks: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
        initUI()
    }

    private fun initComponent() {
        rvCategories=findViewById(R.id.rvCategories)
        rvTasks=findViewById(R.id.rvTasks)
    }

    private fun initUI() {
        categoriesAdapter= CategoriesAdapter(categories)
        rvCategories.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter=categoriesAdapter
    }
}
