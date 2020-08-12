package com.nokhyun.todolist.common

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    protected abstract fun init();
    protected abstract fun setListener();
    protected abstract fun setRecyclerView();
    protected abstract fun setToolBar();


}