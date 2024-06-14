package com.nnech.Ndiulor.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(protected var list: List<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false), viewType
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(list[position])
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(position, list[position])
    }

    abstract fun getLayoutId(position: Int, obj: T): Int
    abstract fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder
}