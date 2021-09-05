package com.mj.subwayapp.presenter

interface BaseView<PresenterT: BasePresenter> {

    val presenter: PresenterT
}