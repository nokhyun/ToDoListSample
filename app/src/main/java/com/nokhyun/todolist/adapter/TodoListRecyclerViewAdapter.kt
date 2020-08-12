package com.nokhyun.todolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nokhyun.todolist.R
import com.nokhyun.todolist.model.TodoListModel

class TodoListRecyclerViewAdapter :
    RecyclerView.Adapter<TodoListRecyclerViewAdapter.TodoListRecyclerViewHolder>() {
    private lateinit var todoListModelItems: ArrayList<TodoListModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListRecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_todolist, parent, false)
        return TodoListRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = todoListModelItems.size
    override fun onBindViewHolder(holder: TodoListRecyclerViewHolder, position: Int) {

        holder.init(todoListModelItems[position])
    }

    inner class TodoListRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var title: TextView

        fun init(todoListModel: TodoListModel) {
            title = itemView.findViewById(R.id.tv_title)

            onBind(todoListModel)
        }

        private fun onBind(todoListModel: TodoListModel) {
            title.text = todoListModel.title
        }

        private fun setListener(){

        }
    }

    fun updateData(todoListModelItems: ArrayList<TodoListModel>) {
        this.todoListModelItems = todoListModelItems
        notifyDataSetChanged()
    }

}