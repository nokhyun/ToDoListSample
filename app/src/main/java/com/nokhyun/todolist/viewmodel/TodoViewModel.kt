package com.nokhyun.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nokhyun.todolist.TodoRepository
import com.nokhyun.todolist.database.entity.MyTodo

// ViewModel
class TodoViewModel(application: Application): AndroidViewModel(application){
    private val todoRepository: TodoRepository by lazy{
        TodoRepository(application)
    }

    fun getAllDatas() = todoRepository.getTodoAll()

    fun insert(myTodo: MyTodo){
        todoRepository.insert(myTodo)
    }

    fun delete(num: Int){
        todoRepository.delete(num)
    }
}