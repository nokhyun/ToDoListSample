package com.nokhyun.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nokhyun.todolist.database.dao.MyTodoDao
import com.nokhyun.todolist.database.entity.MyTodo

@Database(entities = [MyTodo::class], version = 1, exportSchema = false)
abstract class MyTodoDatabase: RoomDatabase() {
    abstract fun todoDao(): MyTodoDao

    companion object {
        private var instance: MyTodoDatabase? = null

        fun getInstance(context: Context): MyTodoDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
            }
        }
        private fun buildDatabase(context: Context): MyTodoDatabase {
            return Room.databaseBuilder(context.applicationContext, MyTodoDatabase::class.java, "my-todoList")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                }).build()
        }
    }

}