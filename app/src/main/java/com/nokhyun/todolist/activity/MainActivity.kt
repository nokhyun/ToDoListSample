package com.nokhyun.todolist.activity

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.CoroutinesRoom
import androidx.room.Room
import com.nokhyun.todolist.R
import com.nokhyun.todolist.adapter.TodoListRecyclerViewAdapter
import com.nokhyun.todolist.common.BaseActivity
import com.nokhyun.todolist.database.MyTodoDatabase
import com.nokhyun.todolist.database.entity.MyTodo
import com.nokhyun.todolist.model.TodoListModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {
    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var list:ArrayList<TodoListModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun init() {
        list = ArrayList()


        CoroutineScope(Dispatchers.IO).launch {
            val a = MyTodoDatabase.getInstance(this@MainActivity).todoDao().getAll()
            a.forEach { Log.d(TAG, "result: $it") }
        }
        setToolBar()
        setRecyclerView()
        setListener()
    }

    override fun setListener() {

    }

    override fun setRecyclerView() {
        for(i in 0 until 20){
            list.add(TodoListModel(i.toString()))
        }

        val todoListRecyclerViewAdapter = TodoListRecyclerViewAdapter()

        rv_main.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = todoListRecyclerViewAdapter
        }

        todoListRecyclerViewAdapter.updateData(list)


    }

    override fun setToolBar() {
        tb_main.title = getString(R.string.title)
    }


}