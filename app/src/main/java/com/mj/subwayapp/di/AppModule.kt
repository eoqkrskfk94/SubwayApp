package com.mj.subwayapp.di

import android.app.Activity
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.mj.subwayapp.data.api.StationApi
import com.mj.subwayapp.data.api.StationArrivalsApi
import com.mj.subwayapp.data.api.StationStorageApi
import com.mj.subwayapp.data.api.Url
import com.mj.subwayapp.data.db.AppDatabase
import com.mj.subwayapp.data.preference.PreferenceManager
import com.mj.subwayapp.data.preference.SharedPreferenceManager
import com.mj.subwayapp.data.repository.StationRepository
import com.mj.subwayapp.data.repository.StationRepositoryImpl
import com.mj.subwayapp.presentation.stationArrivals.StationArrivalsContract
import com.mj.subwayapp.presentation.stationArrivals.StationArrivalsFragment
import com.mj.subwayapp.presentation.stationArrivals.StationArrivalsPresenter
import com.mj.subwayapp.presentation.stations.StationsContract
import com.mj.subwayapp.presentation.stations.StationsFragment
import com.mj.subwayapp.presentation.stations.StationsPresenter
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {

    single { Dispatchers.IO }

    //Database
    single { AppDatabase.build(androidApplication()) }
    single { get<AppDatabase>().stationDao() }

    //Preference
    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) }
    single<PreferenceManager> {SharedPreferenceManager(get())}

    //Repository
    single<StationRepository> { StationRepositoryImpl(get(), get(), get(), get(), get())}

    //Presentation
    scope<StationsFragment> {
        scoped<StationsContract.Presenter> { StationsPresenter(getSource(), get()) }
    }

    scope<StationArrivalsFragment> {
        scoped<StationArrivalsContract.Presenter> { StationArrivalsPresenter(getSource(), get(), get()) }
    }

    //Api
    single<StationApi> {StationStorageApi(Firebase.storage)}


    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }

    single<StationArrivalsApi> {
        Retrofit.Builder().baseUrl(Url.SEOUL_DATA_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }


}