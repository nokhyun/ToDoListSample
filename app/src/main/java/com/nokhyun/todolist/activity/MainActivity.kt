package com.nokhyun.todolist.activity

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.CoroutinesRoom
import androidx.room.Room
import com.nokhyun.todolist.R
import com.nokhyun.todolist.TodoRepository
import com.nokhyun.todolist.adapter.TodoListRecyclerViewAdapter
import com.nokhyun.todolist.common.BaseActivity
import com.nokhyun.todolist.database.MyTodoDatabase
import com.nokhyun.todolist.database.dao.MyTodoDao
import com.nokhyun.todolist.database.entity.MyTodo
import com.nokhyun.todolist.model.TodoListModel
import com.nokhyun.todolist.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private val imm: InputMethodManager by lazy { getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    private var myTodoDatas: LiveData<List<MyTodo>>? = null
    private lateinit var todoListRecyclerViewAdapter: TodoListRecyclerViewAdapter

    private val todoViewModel: TodoViewModel by lazy { TodoViewModel(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun init() {
        setToolBar()
        setRecyclerView()
        setListener()

        myTodoDatas = todoViewModel.getAllDatas()
        myTodoDatas?.observe(this, Observer {
            todoListRecyclerViewAdapter.updateData(it)
        })

    }

    override fun setListener() {
        bt_addList.setOnClickListener {
            var content = ""
            if (!TextUtils.isEmpty(et_listContent.text.toString())) {
                content = et_listContent.text.toString()
                todoViewModel.insert(MyTodo(null, content))
                et_listContent.text = null
                imm.hideSoftInputFromWindow(et_listContent.windowToken, 0)
            }
        }
    }

    override fun setRecyclerView() {
        todoListRecyclerViewAdapter = TodoListRecyclerViewAdapter(todoViewModel)

        rv_main.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = todoListRecyclerViewAdapter
        }
    }

    override fun setToolBar() {
    }


}