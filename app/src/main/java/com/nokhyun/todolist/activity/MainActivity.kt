package com.nokhyun.todolist.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nokhyun.todolist.R
import com.nokhyun.todolist.adapter.TodoListRecyclerViewAdapter
import com.nokhyun.todolist.common.BaseActivity
import com.nokhyun.todolist.model.TodoListModel
import kotlinx.android.synthetic.main.activity_main.*

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