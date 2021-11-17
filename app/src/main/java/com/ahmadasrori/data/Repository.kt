package com.ahmadasrori.data

import com.ahmadasrori.wheel.model.MainModel
import com.ahmadasrori.wheel.remote.Api
import io.reactivex.Observable

class Repository(private val api: Api) {

    fun getRPM(): Observable<List<MainModel>> {
        return api.getRPM()
    }

}