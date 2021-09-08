package com.mj.subwayapp.presentation

interface BaseView<PresenterT: BasePresenter> {

    val presenter: PresenterT
}