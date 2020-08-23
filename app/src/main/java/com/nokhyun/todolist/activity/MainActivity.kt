package com.nokhyun.todolist.activity

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nokhyun.todolist.R
import com.nokhyun.todolist.adapter.ToDoListAdapter
import com.nokhyun.todolist.common.BaseActivity
import com.nokhyun.todolist.database.entity.MyTodo
import com.nokhyun.todolist.databinding.ActivityMainBinding
import com.nokhyun.todolist.viewmodel.TodoViewModel

class MainActivity : BaseActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
    private val imm: InputMethodManager by lazy { getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    private var myTodoDatas: LiveData<List<MyTodo>>? = null
    // default
//    private lateinit var todoListRecyclerViewAdapter: TodoListRecyclerViewAdapter

    // databinding
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoListRecyclerViewAdapter: ToDoListAdapter

    private val todoViewModel: TodoViewModel by lazy { TodoViewModel(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    override fun init() {
        setToolBar()
        setRecyclerView()
        setListener()

        myTodoDatas = todoViewModel.getAllDatas()
        myTodoDatas?.observe(this, Observer {
            todoListRecyclerViewAdapter.updateData(it)
//            todoListRecyclerViewAdapter.update(it)
        })

    }

    override fun setListener() {
        binding.btAddList.setOnClickListener {
            var content = ""
            if (!TextUtils.isEmpty(binding.etListContent.text.toString())) {
                content = binding.etListContent.text.toString()
                todoViewModel.insert(MyTodo(null, content))
                binding.etListContent.text = null
                imm.hideSoftInputFromWindow(binding.etListContent.windowToken, 0)
            }
        }
    }

    override fun setRecyclerView() {
        // default
//        todoListRecyclerViewAdapter = TodoListRecyclerViewAdapter(todoViewModel)
//
//        rv_main.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = todoListRecyclerViewAdapter
//        }

        // databinding
        todoListRecyclerViewAdapter = ToDoListAdapter(todoViewModel)

        binding.rvMain.apply {
            setHasFixedSize(true)
            adapter = todoListRecyclerViewAdapter
        }


    }

    override fun setToolBar() {
    }


}