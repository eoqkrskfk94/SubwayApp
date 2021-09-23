package com.mj.subwayapp.presentation.stations

import com.mj.subwayapp.domain.Station
import com.mj.subwayapp.presentation.BasePresenter
import com.mj.subwayapp.presentation.BaseView

interface StationsContract {

    interface View: BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showStations(stations: List<Station>)

    }

    interface Presenter: BasePresenter {

        fun filterStations(query: String)

        fun toggleStationFavorite(station: Station)

    }

}