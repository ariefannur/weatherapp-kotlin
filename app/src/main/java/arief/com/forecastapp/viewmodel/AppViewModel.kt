package arief.com.forecastapp.viewmodel

import android.app.DownloadManager
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import arief.com.forecastapp.model.Query
import arief.com.forecastapp.network.Api
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class AppViewModel(val api:Api) : ViewModel() {

    val data = MutableLiveData<Query>()

    fun getData(){
        api.getData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread()).subscribe(Consumer {
                    data.value = it.query
                    Log.d("AF", "query :: "+Gson().toJson(it.query))
                })
    }
}