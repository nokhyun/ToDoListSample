package com.nokhyun.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nokhyun.todolist.database.entity.MyTodo
import com.nokhyun.todolist.databinding.ListItemTodolistBinding
import com.nokhyun.todolist.databinding.ListItemTodolistBindingImpl
import com.nokhyun.todolist.viewmodel.TodoViewModel

class ToDoListAdapter(private val todoViewModel: TodoViewModel?) :
    RecyclerView.Adapter<ToDoListAdapter.ToDoListAdapterHolder>() {
    private var todoListModelItems: List<MyTodo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListAdapterHolder {
        val binding =
            ListItemTodolistBindingImpl.inflate(LayoutInflater.from(parent.context), parent, false);
        return ToDoListAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoListAdapterHolder, position: Int) {
        holder.onBind(todoListModelItems[position])
    }

    override fun getItemCount(): Int = todoListModelItems.size

    // 기존방식
    fun updateData(datas: List<MyTodo>?) {
        datas?.let {
            this.todoListModelItems = it
            notifyDataSetChanged()
        }
    }

    inner class ToDoListAdapterHolder(
        private val binding: ListItemTodolistBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(myTodo: MyTodo) {
            binding.myTodo = myTodo
            binding.executePendingBindings()

            setListener()
        }

        private fun setListener() {
            binding.tvTitle.setOnClickListener {
                todoListModelItems[adapterPosition].num?.let { number ->
                    todoViewModel?.delete(
                        number
                    )
                }
            }
        }
    }

    fun update(datas: List<MyTodo>?) {
        datas?.let {
            val diffResult = DiffUtil.calculateDiff(TodoDiffUtil(todoListModelItems, datas))
            todoListModelItems = datas
            diffResult.dispatchUpdatesTo(this)
        }

    }


    /*
    * oldItem과 newItem의 차이를 계산하고 변환하는 업데이트 작업 목록을 출력 할 수있는 유틸리티 클래스.
    * Eugene W. Myers 의 difference algorithm 을 사용하여 하나의 목록을 다른 목록으로 변환하기위한 최소 업데이트 수를 계산한다.
    *
    * 주의사항
    * 리스트 사이즈가 큰 경우는 DiffResult를 가져올때 Background Thread 에서 실행 하고 뷰 갱신 할때만 메인스레드에서 실행 시켜야한다.
    * DiffUtil 사용 할 때 리스트의 Max Size 는 2^26 이다.
    */
    private class TodoDiffUtil(
        private val oldItem: List<MyTodo>,
        private val newItem: List<MyTodo>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItem.size

        override fun getNewListSize(): Int = newItem.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItem[oldItemPosition] == newItem[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            areItemsTheSame(oldItemPosition, newItemPosition)

    }
}