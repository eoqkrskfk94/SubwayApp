package com.mj.subwayapp.presentation.stationArrivals

import com.mj.subwayapp.data.repository.StationRepository
import com.mj.subwayapp.domain.Station
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception

class StationArrivalsPresenter(
    private val view: StationArrivalsContract.View,
    private val station: Station,
    private val stationRepository: StationRepository
):  StationArrivalsContract.Presenter {


    override val scope = MainScope()


    override fun onViewCreated() {
        fetchStationArrivals()
    }

    override fun onDestroyView() {}


    override fun fetchStationArrivals() {
        scope.launch {
            try {
                view.showLoadingIndicator()
                view.showStationArrivals(stationRepository.getStationArrivals(station.name))
            } catch (exception: Exception) {
                exception.printStackTrace()
                view.showErrorDescription(exception.message ?: "error")
            } finally {
                view.hideLoadingIndicator()
            }
        }
    }


}