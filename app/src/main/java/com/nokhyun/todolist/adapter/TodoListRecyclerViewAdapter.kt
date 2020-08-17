package com.nokhyun.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nokhyun.todolist.R
import com.nokhyun.todolist.database.entity.MyTodo
import com.nokhyun.todolist.viewmodel.TodoViewModel

class TodoListRecyclerViewAdapter(private val todoViewModel: TodoViewModel) :
    RecyclerView.Adapter<TodoListRecyclerViewAdapter.TodoListRecyclerViewHolder>() {
    private var todoListModelItems: List<MyTodo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListRecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_todolist, parent, false)
        return TodoListRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = todoListModelItems.size
    override fun onBindViewHolder(holder: TodoListRecyclerViewHolder, position: Int) {

        holder.onBind(todoListModelItems[position])
    }

    inner class TodoListRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var title: TextView = itemView.findViewById(R.id.tv_title)

        fun onBind(todoListModel: MyTodo) {
            title.text = todoListModel.todo

            setListener(todoListModel)
        }

        private fun setListener(todoListModel: MyTodo) {
            itemView.setOnClickListener {
                todoViewModel.delete(todoListModel.num!!)
            }
        }
    }

    fun updateData(todoListModelItems: List<MyTodo>) {
        this.todoListModelItems = todoListModelItems
        notifyDataSetChanged()
    }

}