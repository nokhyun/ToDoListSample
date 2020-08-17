package com.nokhyun.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nokhyun.todolist.database.MyTodoDatabase
import com.nokhyun.todolist.database.entity.MyTodo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Repository
class TodoRepository(application: Application): AndroidViewModel(application) {
    private val coroutineScope by lazy { CoroutineScope(Dispatchers.IO) }
    private val todoDao by lazy { MyTodoDatabase.getInstance(application).todoDao() }
    private val myTodoDatas by lazy {
        todoDao.getAll()
    }

    fun getTodoAll() = myTodoDatas
    fun insert(myTodo: MyTodo) {
        coroutineScope.launch {
            todoDao.insertAll(myTodo)
        }
    }

    fun delete(num: Int){
        coroutineScope.launch {
            todoDao.deleteById(num)
        }
    }
}