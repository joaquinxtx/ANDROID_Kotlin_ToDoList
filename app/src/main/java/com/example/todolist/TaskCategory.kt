package com.example.todolist

sealed class TaskCategory {

    object Personal : TaskCategory()
    object Business : TaskCategory()
    object Other : TaskCategory()
}