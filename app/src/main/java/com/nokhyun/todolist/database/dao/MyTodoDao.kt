package com.nokhyun.todolist.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nokhyun.todolist.database.entity.MyTodo

@Dao
interface MyTodoDao {
    @Query("SELECT * FROM todoList")
    fun getAll(): LiveData<List<MyTodo>>

    // OnConflictStrategy.REPLACE 중복된 값이 들어왔을 떄 그 값으로 변
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(myTodos: MyTodo)

    @Query("DELETE FROM todoList WHERE num = :num")
    fun deleteById(num: Int)
}