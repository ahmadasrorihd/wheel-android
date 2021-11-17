package com.ahmadasrori.wheel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmadasrori.data.Repository
import com.ahmadasrori.wheel.model.MainModel
import com.ahmadasrori.wheel.ui.MainActivity
import com.ahmadasrori.wheel.util.Constant.REMOTE.API_STATUS_SUCCESS
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.InputStream

class MainViewModel(
    private val repository: Repository
    ) : ViewModel() {

    val rpm = MutableLiveData<MainModel>()
    val apiResponse = MutableLiveData<String>()

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun getRPM() {
        compositeDisposable.add(
            repository.getRPM()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    val response = it
                    if (response[0].status==API_STATUS_SUCCESS) {
                        rpm.postValue(response[0])
                        apiResponse.postValue("SPEED UPDATED")
                    } else {
                        apiResponse.postValue("UNABLE TO UPDATE SPEED")
                    }
                }, {
                    apiResponse.postValue("UNABLE TO UPDATE SPEED")
                })
        )
    }

}