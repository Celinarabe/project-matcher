package com.example.project_matcher.base

import com.example.project_matcher.MainPresenter

interface BaseView<T> {
    fun setPresenter(presenter: T)
}
