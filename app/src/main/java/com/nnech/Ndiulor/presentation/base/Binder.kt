package com.nnech.Ndiulor.presentation.base

internal interface Binder<T> {
    fun bind(data: T)
}