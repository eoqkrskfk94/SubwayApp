package com.mj.subwayapp.presentation.stationArrivals

import com.mj.subwayapp.domain.ArrivalInformation
import com.mj.subwayapp.presentation.BasePresenter
import com.mj.subwayapp.presentation.BaseView

interface StationArrivalsContract {

    interface View: BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showErrorDescription(message: String)

        fun showStationArrivals(arrivalInformation: List<ArrivalInformation>)
    }

    interface Presenter: BasePresenter {

        fun fetchStationArrivals()

        fun toggleStationFavorite()

    }
}