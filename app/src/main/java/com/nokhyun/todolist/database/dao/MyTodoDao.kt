package com.nokhyun.todolist.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nokhyun.todolist.database.entity.MyTodo

@Dao
interface MyTodoDao {
    @Query("SELECT * From todoList")
    fun getAll(): List<MyTodo>

    @Insert
    fun insertAll(vararg myTodos: MyTodo)

    @Delete
    fun delete(myTodo: MyTodo)
}