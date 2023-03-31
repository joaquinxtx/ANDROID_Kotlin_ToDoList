package com.example.todolist

data class Task( val name:String,
                 val category: TaskCategory,
                 var isSelected:Boolean=false
)
