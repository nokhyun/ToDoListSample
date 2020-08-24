package com.nokhyun.todolist.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nokhyun.todolist.adapter.ToDoListAdapter
import com.nokhyun.todolist.database.entity.MyTodo

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("listData")
    fun bindRecyclerView(recyclerView: RecyclerView, datas :List<MyTodo>?){
//        val adapter = recyclerView.adapter as TodoListRecyclerViewAdapter
        val adapter = recyclerView.adapter as ToDoListAdapter
//        adapter.updateData(datas)
        adapter.update(datas)
    }
}