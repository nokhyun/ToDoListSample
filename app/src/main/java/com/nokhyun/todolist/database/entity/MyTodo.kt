package com.nokhyun.todolist.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoList")
data class MyTodo (
    @PrimaryKey(autoGenerate = true) val num: Int?,
    @ColumnInfo(name = "todo") val todo: String
)